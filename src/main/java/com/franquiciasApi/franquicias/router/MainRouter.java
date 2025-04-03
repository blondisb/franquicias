package com.franquiciasApi.franquicias.router;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import com.franquiciasApi.franquicias.handler.*;
@Configuration
public class MainRouter {

    private static final String PATH_FRANQUICIAS = "api/franquicias";
    private static final String PATH_SUCURSALES = "api/sucursales";
    private static final String PATH_PRODUCTOS = "api/productos";
    private static final String PATH_ADD_LOADOUT = "api/agregarinventario"; // Cambia esto si es necesario
    private static final String PATH_DECREASE_LOADOUT = "api/disminuirinventario"; // Cambia esto si es necesario
    private static final String PATH_DELETE_LOADOUT = "api/eliminarinventario"; // Cambia esto si es necesario
    private static final String ID_PATH = "/{id}";
    private static final String PATH_SUCURSAL_ID = "/{sucursalId}";
    private static final String PATH_QUANTITY = "/{quantity}"; // Cantidad de los productos a disminuir
    @Bean
    RouterFunction<ServerResponse> router(FranquiciasHandler franquiciasHandler, SucursalesHandler sucursalesHandler, ProductossHandler productosHandler, ProdPorSucHandler prodPorSucHandler) {
        return RouterFunctions.route()
                .GET(PATH_FRANQUICIAS, franquiciasHandler::getAllFranquicias)
                .GET(PATH_FRANQUICIAS + ID_PATH, franquiciasHandler::getFranquiciaById)
                .POST(PATH_FRANQUICIAS, franquiciasHandler::saveFranquicia)
                .PUT(PATH_FRANQUICIAS + ID_PATH, franquiciasHandler::updateFranquicia)
                .DELETE(PATH_FRANQUICIAS + ID_PATH, franquiciasHandler::deleteFranquiciaById) // Implement this method
                // --
                .GET(PATH_SUCURSALES, sucursalesHandler::getAllSucursales)
                .GET(PATH_SUCURSALES + ID_PATH, sucursalesHandler::getSucursalById)
                .POST(PATH_SUCURSALES, sucursalesHandler::saveSucursal)
                .PUT(PATH_SUCURSALES + ID_PATH, sucursalesHandler::updateSucursal)
                .DELETE(PATH_SUCURSALES + ID_PATH, sucursalesHandler::deleteSucursalById) // Implement this method
                // --
                .GET(PATH_PRODUCTOS, productosHandler::getAllProductos)
                .GET(PATH_PRODUCTOS + ID_PATH, productosHandler::getProductsById)
                .POST(PATH_PRODUCTOS, productosHandler::saveProducto)
                .PUT(PATH_PRODUCTOS + ID_PATH, productosHandler::updateProducts)
                .DELETE(PATH_PRODUCTOS + ID_PATH, productosHandler::deleteProductsById) // Implement this method
                // --
                .POST(PATH_ADD_LOADOUT, prodPorSucHandler::saveOrUpdateProducto)
                .PUT(PATH_DECREASE_LOADOUT + ID_PATH + PATH_QUANTITY, prodPorSucHandler::decreaseStock)
                .DELETE(PATH_DELETE_LOADOUT + ID_PATH + PATH_SUCURSAL_ID, prodPorSucHandler::deleteLoadutByIds) //  ID_PATH (idProducto) + ID_PATH (idSucursal)
                // --
                .build();
    }
}