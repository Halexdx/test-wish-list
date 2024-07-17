import { describe, it, expect } from 'vitest'

import { mount } from '@vue/test-utils'
import BreadcrumbComponent from '../BreadcrumbComponent.vue'

describe('BreadcrumbComponent', () => {
  it('renders basic bread crumb', () => {
    const previous = {
      name: 'Home',
      path: '/'
    }
    const wrapper = mount(BreadcrumbComponent, { props: { current: 'WishList', previous } })
    expect(wrapper.text()).toContain('Home')
    expect(wrapper.text()).toContain('WishList')
  })
})
