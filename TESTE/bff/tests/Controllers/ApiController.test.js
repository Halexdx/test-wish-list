const request = require("supertest");
const express = require('express');
const apiController = require('../../controllers/ApiController');
const ApiService = require('../../services/ApiService');
const MockAdapter = require("axios-mock-adapter");
const axios = require("axios");

const app = express();
app.use(express.json());
app.use('/', apiController);

describe('apiController tests', () => {
  let mock;
  const urlBase = "http://localhost:8080";

  beforeEach(() => {
    mock = new MockAdapter(axios);
  });

  afterEach(() => {
    mock.restore();
  });

  it('should return items list', async () => {
    const mockData = [{ total: 1, pageSize: 1, products: {} }];
    mock.onGet().reply(200, { data: mockData });

    const response = await request(app).get('/items');
    expect(response.status).toBe(200);
    expect(response.body.items).toBeTruthy();
  });

  it('should return a empty list when cant find a list of items', async () => {
    mock.onGet().reply(404, { error: "NOT FOUND"});

    const response = await request(app).get('/items');
    expect(response.status).toBe(200);
    expect(response.body.items).toBeTruthy();
    expect(response.body.items).toHaveLength(0);
  });

  it('should return wishlist by user', async () => {
    const mockData = [{ item: ['ASK-13-00', 'ASK-13-02']}];
    mock.onGet(`${urlBase}/wishlist/list?id=1`).reply(201, { data: mockData })
    
    const response = await request(app).get('/wishlist/1');
    expect(response.status).toBe(200);
    expect(response).toBeTruthy();
  });

  it('should return 404 when try to get wishlist by invalid user', async () => {
    mock.onGet(`${urlBase}/wishlist/list?id=2`).reply(404, { error: "NOT FOUND" })
    
    const response = await request(app).get('/wishlist/2');
    expect(response.status).toBe(404);
    expect(response.body.error).toBe('Erro lista de desejos não escontrada');
  });

  it('should add a new item to wishlist', async () => {
    const newItem = { user_id: 123, product_code: "ASK-13-03"};
    mock.onPost(`${urlBase}/wishlist/add`).reply(201, "1234");

    const response = await request(app)
      .post('/wishlist')
      .send(newItem);
    expect(response.status).toBe(200);
    expect(response.body.message).toBe('Novo item criado');
  });

  it('should return 500 when try to add a invalid item to wishlist', async () => {
    const invalidItem = { user_id: 123 };
    mock.onPost(`${urlBase}/wishlist/add`).reply(500, {error: "ERROR"});
    const response = await request(app)
      .post('/wishlist')
      .send(invalidItem);
    expect(response.status).toBe(500);
    expect(response.body.error).toBe('Erro ao adicionar produto');
  });

  it('should remove a item of wishlist when receive a userId and a product code', async () => {
    const userId = '1';
    const itemCode = 'ABC123';
    mock.onDelete(`${urlBase}/wishlist/remove?userId=${userId}&productCode=${itemCode}`).reply(204, "NOT CONTENT");

    const response = await request(app).delete(`/wishlist/${userId}/${itemCode}`);
    expect(response.status).toBe(200);
    expect(response.body.message).toBe('Item excluído com sucesso');
  });

  it('should return 404 when try to remove a item wish invalid user', async () => {
    const nonExistentUserId = '999';
    mock.onDelete(`${urlBase}/wishlist/remove?userId=${nonExistentUserId}`).reply(404,{error: "NOT FOUND"});
    const response = await request(app).delete(`/wishlist/${nonExistentUserId}`);
    console.log(response.status)
    expect(response.status).toBe(404);
  });

});