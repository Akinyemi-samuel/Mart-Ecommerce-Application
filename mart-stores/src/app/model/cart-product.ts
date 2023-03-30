export class CartProduct {
    public get productPrice(): number {
        return this._productPrice;
    }
    public set productPrice(value: number) {
        this._productPrice = value;
    }
    public get productQuantity(): number {
        return this._productQuantity;
    }
    public set productQuantity(value: number) {
        this._productQuantity = value;
    }
    public get productImg(): string {
        return this._productImg;
    }
    public set productImg(value: string) {
        this._productImg = value;
    }
    public get productId(): string {
        return this._productId;
    }
    public set productId(value: string) {
        this._productId = value;
    }
    public get productName(): string {
        return this._productName;
    }
    public set productName(value: string) {
        this._productName = value;
    }
    public get id(): number {
        return this._id;
    }
    public set id(value: number) {
        this._id = value;
    }

    constructor(
        private _id: number,
        private _productName: string,
        private _productId: string,
        private _productImg: string,
        private _productQuantity: number,
        private _productPrice: number,
        ) {
        
    }
}
