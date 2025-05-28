package com.cobb.master_data_lookup.utils;

public class UrlMapping {

    public static final String API = "/api";
    public static final String VERSION_V1 = "/v1";
    public static final String MASTER_DATA_LOOKUP = "/master-data-lookup";

    /*============================ Start Availability Check API ========================================*/
    public static final String AVAILABILITY_CHECK = MASTER_DATA_LOOKUP + API + VERSION_V1 + "/availability-check";
    public static final String AVAILABILITY_CHECK_CREATE = "/create";
    public static final String AVAILABILITY_CHECK_UPDATE = "/{id}/update";
    public static final String AVAILABILITY_CHECK_DELETE = "/{id}/delete";
    public static final String AVAILABILITY_CHECK_GET_ALL = "/all";
    public static final String AVAILABILITY_CHECK_GET_BY_ID = "/{id}/availability-check";
    /*============================ End Availability Check API ==========================================*/

    /*============================ Start Item Category API ========================================*/
    public static final String ITEM_CATEGORY = MASTER_DATA_LOOKUP + API + VERSION_V1 + "/item-categorys";
    public static final String ITEM_CATEGORY_CREATE = "/item-category/create";
    public static final String ITEM_CATEGORY_UPDATE = "/item-category/{id}/update";
    public static final String ITEM_CATEGORY_DELETE = "/item-category/{id}/delete";
    public static final String ITEM_CATEGORY_GET_ALL = "/all";
    public static final String ITEM_CATEGORY_GET_BY_ID = "/{id}/item-category";
    /*============================ End Item Category API ==========================================*/

    /*============================ Start Material Group API ========================================*/
    public static final String MATERIAL_GROUP = MASTER_DATA_LOOKUP + API + VERSION_V1 + "/material-groups";
    public static final String MATERIAL_GROUP_CREATE = "/material-group/create";
    public static final String MATERIAL_GROUP_UPDATE = "/material-group/{id}/update";
    public static final String MATERIAL_GROUP_DELETE = "/material-group/{id}/delete";
    public static final String MATERIAL_GROUP_GET_ALL = "/all";
    public static final String MATERIAL_GROUP_GET_BY_ID = "/{id}/material-group";
    /*============================ End Material Group API ==========================================*/

    /*============================ Start Material Type API ========================================*/
    public static final String MATERIAL_TYPE = MASTER_DATA_LOOKUP + API + VERSION_V1 + "/material-types";
    public static final String MATERIAL_TYPE_CREATE = "/material-type/create";
    public static final String MATERIAL_TYPE_UPDATE = "/material-type/{id}/update";
    public static final String MATERIAL_TYPE_DELETE = "/material-type/{id}/delete";
    public static final String MATERIAL_TYPE_GET_ALL = "/all";
    public static final String MATERIAL_TYPE_GET_BY_ID = "/{id}/material-type";
    /*============================ End Material Type API ==========================================*/

    /*============================ Start MRP Controller API ========================================*/
    public static final String MRP_CONTROLLER = MASTER_DATA_LOOKUP + API + VERSION_V1 + "/mrps";
    public static final String MRP_CONTROLLER_CREATE = "/mrp/create";
    public static final String MRP_CONTROLLER_UPDATE = "/mrp/{id}/update";
    public static final String MRP_CONTROLLER_DELETE = "/mrp/{id}/delete";
    public static final String MRP_CONTROLLER_GET_ALL = "/all";
    public static final String MRP_CONTROLLER_GET_BY_ID = "/{id}/mrp";
    /*============================ End MRP Controller API ==========================================*/

    /*============================ Start MRP Type API ========================================*/
    public static final String MRP_TYPE = MASTER_DATA_LOOKUP + API + VERSION_V1 + "/mrp-types";
    public static final String MRP_TYPE_CREATE = "/mrp-type/create";
    public static final String MRP_TYPE_UPDATE = "/mrp-type/{id}/update";
    public static final String MRP_TYPE_DELETE = "/mrp-type/{id}/delete";
    public static final String MRP_TYPE_GET_ALL = "/all";
    public static final String MRP_TYPE_GET_BY_ID = "/{id}/mrp-type";
    /*============================ End MRP Type API ==========================================*/

    /*============================ Start Plants API ========================================*/
    public static final String PLANT = MASTER_DATA_LOOKUP + API + VERSION_V1 + "/plants";
    public static final String PLANT_CREATE = "/plant/create";
    public static final String PLANT_UPDATE = "/plant/{id}/update";
    public static final String PLANT_DELETE = "/plant/{id}/delete";
    public static final String PLANT_GET_ALL = "/all";
    public static final String PLANT_GET_BY_ID = "/{id}/plant";
    /*============================ End Plants API ==========================================*/

    /*============================ Start Purchasing Group API ========================================*/
    public static final String PURCHASING_GROUP = MASTER_DATA_LOOKUP + API + VERSION_V1 + "/purchasing-groups";
    public static final String PURCHASING_GROUP_CREATE = "/purchasing-group/create";
    public static final String PURCHASING_GROUP_UPDATE = "/purchasing-group/{id}/update";
    public static final String PURCHASING_GROUP_DELETE = "/purchasing-group/{id}/delete";
    public static final String PURCHASING_GROUP_GET_ALL = "/all";
    public static final String PURCHASING_GROUP_GET_BY_ID = "/{id}/purchasing-group";
    /*============================ End Purchasing Group API ==========================================*/

    /*============================ Start Storage Location API ========================================*/
    public static final String STORAGE_LOCATION = MASTER_DATA_LOOKUP + API + VERSION_V1 + "/storage-locations";
    public static final String STORAGE_LOCATION_CREATE = "/storage-location/create";
    public static final String STORAGE_LOCATION_UPDATE = "/storage-location/{id}/update";
    public static final String STORAGE_LOCATION_DELETE = "/storage-location/{id}/delete";
    public static final String STORAGE_LOCATION_GET_ALL = "/all";
    public static final String STORAGE_LOCATION_GET_BY_ID = "/{id}/storage-location";
    /*============================ End Storage Location API ==========================================*/

    /*============================ Start UOM API ========================================*/
    public static final String UOM = MASTER_DATA_LOOKUP + API + VERSION_V1 + "/uoms";
    public static final String UOM_CREATE = "/uom/create";
    public static final String UOM_UPDATE = "/uom/{id}/update";
    public static final String UOM_DELETE = "/uom/{id}/delete";
    public static final String UOM_GET_ALL = "/all";
    public static final String UOM_GET_BY_ID = "/{id}/uom";
    /*============================ End UOM API ==========================================*/

}
