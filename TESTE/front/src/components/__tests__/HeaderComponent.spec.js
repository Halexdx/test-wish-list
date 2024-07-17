import { describe, it, expect } from 'vitest'

import { mount } from '@vue/test-utils'
import HeaderComponent from '../HeaderComponent.vue'

describe('HeaderComponent', () => {
  it('renders basic header', () => {
    const wrapper = mount(HeaderComponent)
    expect(wrapper.text()).toContain('WishList')
  })
})