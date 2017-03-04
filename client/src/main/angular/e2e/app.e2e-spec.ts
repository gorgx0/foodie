import { FoodiePage } from './app.po';

describe('foodie App', () => {
  let page: FoodiePage;

  beforeEach(() => {
    page = new FoodiePage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
