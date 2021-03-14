
export class DailyStocks {
  constructor(
    private _metadata: MetaDataDaily,
    private _timeSeries: {[key: string]: TimeSeriesDaily}
  ) {
  }


  get metadata(): MetaDataDaily {
    return this._metadata;
  }

  set metadata(value: MetaDataDaily) {
    this._metadata = value;
  }

  get timeSeries(): { [p: string]: TimeSeriesDaily } {
    return this._timeSeries;
  }

  set timeSeries(value: { [p: string]: TimeSeriesDaily }) {
    this._timeSeries = value;
  }
}

export class MetaDataDaily {
  constructor(
    private _information: string,
    private _symbol: string,
    private _lastRefreshed: Date,
    private _outputSize: string,
    private _timeZone: string
  ) {
  }

  get information(): string {
    return this._information;
  }

  set information(value: string) {
    this._information = value;
  }

  get symbol(): string {
    return this._symbol;
  }

  set symbol(value: string) {
    this._symbol = value;
  }

  get lastRefreshed(): Date {
    return this._lastRefreshed;
  }

  set lastRefreshed(value: Date) {
    this._lastRefreshed = value;
  }

  get outputSize(): string {
    return this._outputSize;
  }

  set outputSize(value: string) {
    this._outputSize = value;
  }

  get timeZone(): string {
    return this._timeZone;
  }

  set timeZone(value: string) {
    this._timeZone = value;
  }
}

export class TimeSeriesDaily {
  constructor(
    private _open: string,
    private _high: string,
    private _low: string,
    private _close: string,
    private _volume: string
  ) {
  }

  get open(): string {
    return this._open;
  }

  set open(value: string) {
    this._open = value;
  }

  get high(): string {
    return this._high;
  }

  set high(value: string) {
    this._high = value;
  }

  get low(): string {
    return this._low;
  }

  set low(value: string) {
    this._low = value;
  }

  get close(): string {
    return this._close;
  }

  set close(value: string) {
    this._close = value;
  }

  get volume(): string {
    return this._volume;
  }

  set volume(value: string) {
    this._volume = value;
  }
}


