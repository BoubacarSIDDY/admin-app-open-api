export * from './produit.service';
import { ProduitService } from './produit.service';
export * from './role.service';
import { RoleService } from './role.service';
export * from './user.service';
import { UserService } from './user.service';
export const APIS = [ProduitService, RoleService, UserService];
