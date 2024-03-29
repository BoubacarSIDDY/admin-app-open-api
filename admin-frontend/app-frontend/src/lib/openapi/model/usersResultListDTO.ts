/**
 * API de gestion des utilisateurs, roles et produits
 * Documentation du microservices de la gestion des utilisateurs,roles et produits
 *
 * The version of the OpenAPI document: 1.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
import { UserDTO } from './userDTO';


export interface UsersResultListDTO { 
    /**
     * The users\'s list
     */
    appRolesList?: Array<UserDTO>;
}

