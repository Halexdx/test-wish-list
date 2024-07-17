const axios = require('axios');

class ClientService {
    constructor() {
      this.baseUrl =  "http://localhost:8080";
  }
    async getAllProducts() {
        try {
            console.log("Try to get products list")
            const response = await axios.get(`${this.baseUrl}/product/list`);
            console.log("success to get all products")
            return response.data;
        } catch (error) {
            console.error('Erro ao buscar produtos:', error);
            return [];
        }
    }

    async getWishListByClient(id) {
        try {
            console.log("Try to get wishlist");
            const response = await axios.get(`${this.baseUrl}/wishlist/list?id=${id}`);
            console.log("success to get wish list of user id:", id);
            return response.data;
        } catch (error) {
            console.error('Erro ao buscar cliente por ID:', error);
            throw new Error(error);
        }
    }

    async addItem(item) {
        try {
            console.log("Try to add to wishlist");
            const response = await axios.post(`${this.baseUrl}/wishlist/add`, item, {
              headers: {
                  'Content-Type': 'application/json',
              }
          });
            console.log("success add to wishlist");
            return response.data;
        } catch (error) {
            console.error('Erro ao criar novo cliente:', error);
            throw new Error(error);
        }
    }

    async deleteItem(id,code) {
        try {
            console.log("Try to remove item of wishlist");
            const response = await axios.delete(`${this.baseUrl}/wishlist/remove?userId=${id}&productCode=${code}`);
            console.log("success on remove item of wishlist");
            return response.data;
        } catch (error) {
            console.error('Erro ao excluir cliente:', error);
            throw new Error(error);
        }
    }
}

module.exports = ClientService;