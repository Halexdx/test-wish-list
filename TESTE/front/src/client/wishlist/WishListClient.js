import axios from 'axios';


const  url = "http://localhost:8083/api/wishlist";

const wishlistClient = {
  async addWish(item) {
    try {
      const response = await axios.post(url, item);
      return response.data;
    } catch (error) {
      console.error('Erro ao adicionar item à lista de desejos:', error);
      throw error;
    }
  },
  
  async removeWish(user, code) {
    try {
      const response = await axios.delete(url + `/${user}/${code}`);
      return response.data;
    } catch (error) {
      console.error('Erro ao remover item da lista de desejos:', error);
      throw error;
    }
  },
  
  async getWish(user) {
    try {
      const response = await axios.get(url + `/${user}`);
      return response.data;
    } catch (error) {
      console.error('Erro ao obter lista de desejos:', error);
      throw error;
    }
  }
}

export default wishlistClient;