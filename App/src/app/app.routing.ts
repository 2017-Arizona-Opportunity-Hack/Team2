import {WelcomeComponent} from './welcome/welcome.component';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {BillsComponent} from './bills/bills.component';
import {EmployeeComponent} from './employee/employee.component';
import {ImportComponent} from './import/import.component';

const appRoutes: Routes = [
  { path: '', component: WelcomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'bills', component: BillsComponent },
  { path: 'employee', component: EmployeeComponent},
  { path: 'import', component: ImportComponent},
  //404.
  { path: '**', redirectTo: '' }
];

export const routing = RouterModule.forRoot(appRoutes);
