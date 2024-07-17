import axios from 'axios';

const url = "http://localhost:8083/api/items";

async function getProducts() {
  try {
    const response = await axios.get(url);
    return response.data;
  } catch (error) {
    console.error(error);
  }
}

export default getProducts;