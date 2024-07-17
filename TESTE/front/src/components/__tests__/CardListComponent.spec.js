import { describe, it, expect } from 'vitest'

import { mount } from '@vue/test-utils'
import CardListComponent from '../CardListComponent.vue'
import mockProductList from './mock/mockProductList.json'

describe('CardListComponent', () => {
  it('renders basic card with wishView false', () => {
    const wrapper = mount(CardListComponent, { props: { productsList: mockProductList.products, wishs: [], wishView: false } })
    expect(wrapper.text()).toContain('Kit Meia Invisível Olympikus C/ 3 Pares Masculina')
    expect(wrapper.text()).toContain('Tênis Adidas Breaknet Masculino')
    expect(wrapper.text()).toContain('100,00')

    const button = wrapper.find('button');
    expect(button.exists()).toBe(true);

    expect(button.classes()).toContain('add-to-wish-button');
    expect(button.classes()).not.toContain('remove-to-wish-button');

    const buttonImg = wrapper.find('button img');
    expect(buttonImg.exists()).toBe(true);
  })

  it('renders basic card with wishView true', () => {
    const wrapper = mount(CardListComponent, { props: { productsList: mockProductList.products, wishs: [], wishView: true } })
    expect(wrapper.text()).toContain('Kit Meia Invisível Olympikus C/ 3 Pares Masculina')
    expect(wrapper.text()).toContain('Tênis Adidas Breaknet Masculino')
    expect(wrapper.text()).toContain('100,00')
    const button = wrapper.find('button');
    expect(button.exists()).toBe(true);

    expect(button.classes()).toContain('remove-to-wish-button');
    expect(button.classes()).not.toContain('add-to-wish-button');
  })
})