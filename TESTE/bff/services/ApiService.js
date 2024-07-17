const ApiClient = require('../clients/ApiClient');
const ItemDTO = require('../dto/ItemDTO');
const apiClient = new ApiClient();

class ApiService {

  async getProducts() {
    return await apiClient.getAllProducts();
  }

  async getWish(userId) {
      return await apiClient.getWishListByClient(userId);
  }

  async createItem(item) {
      const nItem = new ItemDTO(item);
      return await apiClient.addItem(nItem);
  }

  async deleteItem(id, code) {
      return await apiClient.deleteItem(id, code);
  }
}

module.exports = ApiService;