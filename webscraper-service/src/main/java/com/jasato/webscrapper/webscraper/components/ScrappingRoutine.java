package com.jasato.webscrapper.webscraper.components;

import com.jasato.webscrapper.webscraper.enums.*;
import com.jasato.webscrapper.webscraper.models.*;
import com.jasato.webscrapper.webscraper.repositories.*;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.*;

import java.io.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

@Component
public class ScrappingRoutine {

    Logger logger = LoggerFactory.getLogger(ScrappingRoutine.class);
    @Autowired
    IbexCompanyRepository ibexCompanyRepository;
    @Autowired
    NewsRepository newsRepository;

    @Scheduled(fixedRate = 60 * 60 * 1000)
    void checkNews() {
        if (ibexCompanyRepository.findAll().isEmpty()) {
            addCompanies();
        }
        checkElEconomista();
        checkCincoDias();
        checkElConfidencial();


    }


    private void checkElConfidencial() {
        logger.info("Scrapping El Confidencial");

        for (IbexCompany ibexCompany : ibexCompanyRepository.findAll()) {

            try {
                String url = "https://www.elconfidencial.com/buscar/2-6-1-3/0/1/10/desc/" + ibexCompany.getElConfidencialTagName();
                String title = "";
                String link = "";
                String date = "";


                Document doc = Jsoup.connect(url).get();

                Elements elements = doc.getElementsByTag("article");
                for (Element element : elements) {
                    Elements titles = element.getElementsByTag("img");
                    Elements urls = element.getElementsByClass("new-title");
                    for (Element t : titles) {
                        title = t.attr("title");
                    }
                    for (Element u : urls) {
                        link = u.select("a").get(0).absUrl("href");
                    }

                    Document article = Jsoup.connect(link).get();
                    try {
                        Element dateTimeCreated = article.getElementsByClass("dateTime__created").get(0);
                        date = dateTimeCreated.text();
                    } catch (IndexOutOfBoundsException e) {
                        logger.warn("Problem extracting date for article: " + title);
                    }


                    if (title.toLowerCase().contains(ibexCompany.getProductionName().toLowerCase()) && !newsRepository.findByTitle(title.substring(11)).isPresent() && !date.isEmpty()) {
                        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");

                        logger.info(title);
                        logger.info(link);

                        newsRepository.save(new News(
                                LocalDateTime.parse(date, dateTimeFormatter),
                                title.substring(11),
                                link,
                                MediaOutlet.ELCONFIDENCIAL,
                                ibexCompany
                        ));
                    }


                }

            } catch (IOException i) {
                logger.warn("Error fetching news from El Confidencial");

            }
        }

    }

    private void checkCincoDias() {
        logger.info("Scrapping Cinco Dias");

        for (IbexCompany ibexCompany : ibexCompanyRepository.findAll()) {

            try {

                String url = "https://cincodias.elpais.com/tag/" + ibexCompany.getCincoDiasTagName();
                String title = "";
                String link = "";
                LocalDateTime dateTime = LocalDateTime.now();

                Document doc = Jsoup.connect(url).get();
                Elements news = doc.getElementsByTag("article");
                for (Element e : news) {
                    Elements links = e.getElementsByClass("enlace");
                    Elements titles = e.getElementsByTag("img");
                    Elements datetime = e.getElementsByAttributeValue("itemprop", "datePublished");

                    for (Element u : titles) {
                        title = u.attr("title");

                    }
                    for (Element o : links) {
                        link = e.select("a").get(0).absUrl("href");
                    }
                    for (Element d : datetime) {
                        dateTime = LocalDateTime.parse(d.attr("content"));

                    }

                    if (title.toLowerCase().contains(ibexCompany.getProductionName().toLowerCase())) {

                        if (!newsRepository.findByTitle(title).isPresent()) {
                            logger.info(title);
                            logger.info(link);
                            newsRepository.save(new News(
                                    dateTime,
                                    title,
                                    link,
                                    MediaOutlet.CINCODIAS,
                                    ibexCompany
                            ));
                        }

                    }

                }
            } catch (IOException e) {
                logger.warn("Error fetching news from Cinco dias. Company: " + ibexCompany.getProductionName());


            }
        }

    }

    private void checkElEconomista() {

        logger.info("Scrapping el economista");

        for (IbexCompany ibexCompany : ibexCompanyRepository.findAll()) {


            String url = "https://www.eleconomista.es/empresa/" + ibexCompany.getElEconomistaTagName() + "/noticias";


            try {
                Document doc = Jsoup.connect(url).get();
                Elements noticias = doc.getElementsByClass("article");


                for (Element noticia : noticias) {

                    String title = noticia.getElementsByAttributeValue("itemprop", "headline").text();
                    String url1 = noticia.select("a").get(0).absUrl("href");
                    String dates = noticia.getElementsByAttributeValue("itemprop", "datePublished").text();

                    if (title.toLowerCase().contains(ibexCompany.getProductionName().toLowerCase()) && !newsRepository.findByTitle(title).isPresent()) {
                        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                        logger.info(title);
                        logger.info(url1);

                        newsRepository.save(new News(
                                LocalDateTime.parse(dates.substring(0, 19), dateTimeFormatter),
                                title,
                                url1,
                                MediaOutlet.EL_ECONOMISTA,
                                ibexCompany
                        ));
                    }

                }
            } catch (IOException e) {
                logger.warn("Error fetching news from El Economista");

            }

        }

    }

    private void addCompanies() {

        List<IbexCompany> companies = Arrays.asList(new IbexCompany("ACCIONA","AJ3-FRK", "Acciona", "acciona", "acciona", "acciona"),
                new IbexCompany("ACERINOX", "ACE1.FRK", "Acerinox", "acerinox", "acerinox", "acerinox"),
                new IbexCompany("ACS","OCI1.FRK", "ACS", "acs", "acs", "acs"),
                new IbexCompany("AENA","A44.FRK", "AENA", "aena_aeropuertos", "aena", "aena"),
                new IbexCompany("ALMIRALL","E2Z.FRK", "Almirall", "almirall", "almirall", "almirall"),
                new IbexCompany("AMADEUS", "AMADF", "Amadeus", "amadeus", "amadeus", "amadeus"),
                new IbexCompany("ARCELOR","ARRD.FRK", "ArcelorMittal", "arcelor_mittal", "arcelor-mittal", "ARCELORMITTAL"),
                new IbexCompany("BANKIA","FV02.FRK", "Bankia", "bankia", "bankia", "bankia"),
                new IbexCompany("BBVA","BBVA.FRK", "BBVA", "bbva_banco_bilbao_vizcaya_argentaria", "bbva", "bbva"),
                new IbexCompany("CAIXABANK","48CA.FRK", "CaixaBank", "caixabank", "caixabank", "caixabank"),
                new IbexCompany("CELLNEX","CLNXF", "CellNex Telecom", "cellnex_telecom", "cellnex", "CELLNEX-TELECOM-"),
                new IbexCompany("CIE","CAD.FRK", "CIE Automotive", "cie_automotive", "cie-automotive", "cie-automotive"),
                new IbexCompany("ENAGAS","ENGGF", "Enagás", "enagas", "enagas", "enagas"),
                new IbexCompany("ENDESA","NA.FRK", "Endesa", "endesa", "endesa", "endesa"),
                new IbexCompany("FERROVIAL","UFG.FRK", "Ferrovial", "ferrovial", "ferrovial", "ferrovial"),
                new IbexCompany("GRIFOLS","G0FB.FRK", "Grifols", "laboratorios_grifols", "grifols", "grifols"),
                new IbexCompany("IAG","IAG.LON", "IAG", "iag_international_airlines_group", "iag", "IAG-IBERIA"),
                new IbexCompany("IAG", "IBE1.FRK", "Iberdrola", "iberdrola", "iberdrola", "iberdrola"),
                new IbexCompany("INDITEX","ITXN", "Inditex", "inditex", "inditex", "inditex"),
                new IbexCompany("INDRA","IDA.FRK", "Indra", "indra", "indra", "indra"),
                new IbexCompany("MAPFRE","CMAB.FRK", "Mapfre", "mapfre", "mapfre", "mapfre"),
                new IbexCompany("MELIA","SMIZF", "Meliá Hotels", "melia", "melia", "SOL-MELIA"),
                new IbexCompany("MERLIN","MEQA.FRK", "Merlin Properties", "merlin_properties", "merlin-properties", "MERLIN-PROP"),
                new IbexCompany("NATURGY","GAN.FRK", "Naturgy", "naturgy_energy_group", "naturgy", "naturgy"),
                new IbexCompany("PHARMAMAR","PMRA.FRK", "PharmaMar", "pharmamar", "pharmamar", "PHARMA-MAR-R"),
                new IbexCompany("REDELECTRICA","RDEIF", "Red Eléctrica", "red_electrica", "red-electrica", "REE"),
                new IbexCompany("REPSOL","REPA.FRK", "Repsol", "repsol_petroleo", "repsol", "repsol"),
                new IbexCompany("SANTANDER","SAN", "Santander", "santander", "santander", "santander"),
                new IbexCompany("GAMESA","GTQ1.FRK", "Siemens Gamesa", "siemens_gamesa", "siemens-gamesa", "GAMESA"),
                new IbexCompany("SOLARIA","SEYMF", "Solaria", "solaria", "solaria", "solaria"),
                new IbexCompany("TELEFONICA","TEF", "Telefónica", "telefonica", "telefonica", "telefonica"),
                new IbexCompany("VISCOFAN","VIS.FRK", "Viscofan", "viscofan", "viscofan", "viscofan")
        );

        ibexCompanyRepository.saveAll(companies);
    }

}
