export class Product {
  public get category(): category {
    return this._category;
  }
  public set category(value: category) {
    this._category = value;
  }
  public get lastCreated(): Date {
    return this._lastCreated;
  }
  public set lastCreated(value: Date) {
    this._lastCreated = value;
  }
  public get dateCreated(): Date {
    return this._dateCreated;
  }
  public set dateCreated(value: Date) {
    this._dateCreated = value;
  }
  public get unitsInStock(): number {
    return this._unitsInStock;
  }
  public set unitsInStock(value: number) {
    this._unitsInStock = value;
  }
  public get active(): boolean {
    return this._active;
  }
  public set active(value: boolean) {
    this._active = value;
  }
  public get imageUrl(): string {
    return this._imageUrl;
  }
  public set imageUrl(value: string) {
    this._imageUrl = value;
  }
  public get unitPrice(): number {
    return this._unitPrice;
  }
  public set unitPrice(value: number) {
    this._unitPrice = value;
  }
  public get description(): string {
    return this._description;
  }
  public set description(value: string) {
    this._description = value;
  }
  public get name(): string {
    return this._name;
  }
  public set name(value: string) {
    this._name = value;
  }
  public get id(): number {
    return this._id;
  }
  public set id(value: number) {
    this._id = value;
  }
  constructor(
    private _id: number,
    private _name: string,
    private _description: string,
    private _unitPrice: number,
    private _imageUrl: string,
    private _active: boolean,
    private _unitsInStock: number,
    private _dateCreated: Date,
    private _lastCreated: Date,
    private _category: category
  ) {}

  
}

interface category{
    id:number,
    categoryName:string
}
