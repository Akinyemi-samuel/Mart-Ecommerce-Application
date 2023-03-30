export class ProductStatus {
  public get statusName(): string {
      return this._statusName;
  }
  public set statusName(value: string) {
      this._statusName = value;
  }
  public get id(): number {
      return this._id;
  }
  public set id(value: number) {
      this._id = value;
  }
  constructor(private _id: number, private _statusName: string) {}
}
