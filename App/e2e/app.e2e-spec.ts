import { PaypalHackathonPage } from './app.po';

describe('paypal-hackathon App', function() {
  let page: PaypalHackathonPage;

  beforeEach(() => {
    page = new PaypalHackathonPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
