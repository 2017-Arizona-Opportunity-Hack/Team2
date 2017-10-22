import {WelcomeComponent} from './welcome/welcome.component';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {BillsComponent} from './bills/bills.component';
import {EmployeeComponent} from './employee/employee.component';

const appRoutes: Routes = [
  { path: '', component: WelcomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'bills', component: BillsComponent },
  { path: 'employee', component: EmployeeComponent},
  //404.
  { path: '**', redirectTo: '' }
];

export const routing = RouterModule.forRoot(appRoutes);
