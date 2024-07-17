class ItemDTO{
  user_id;
  product_code;

  constructor(data) {
    this.user_id = data.user_id;
    this.product_code = data.product_code;
  }
}

module.exports = ItemDTO;