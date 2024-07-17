// https://on.cypress.io/api

describe('Test products list and wishlist', () => {
  it('Navega para a página, clica em elementos e muda de página', () => {
    // First Page
    cy.visit('/', { timeout: 3000 }); 
    cy.get('.card-content').should('exist');
    cy.get('.wrapper-header').should('exist');
    cy.get('button.add-to-wish-button').first().click();
    
    // Wishlist page
    cy.get('a[href="/wishlist"].wrapper-wish-list').click();
    cy.url().should('include', '/wishlist');
    cy.get('.card-content').should('exist');
    cy.get('.wrapper-header').should('exist');
    cy.get('button.remove-to-wish-button').click();
    cy.get('.wrapper-empty-wishs').should('exist').and('be.visible');
    cy.end();
  });
})
