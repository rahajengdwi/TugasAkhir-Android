package com.example.cobata.model;

public class ProductModel {
    int id;
    String name;
    String description;
    String description_sale;
    String type;
    String default_code;
    String barcode;
    String categ_id;
    String lst_price;
    String standard_price;
    String sales_count;
    String active;
    String invoice_policy;
    String expense_policy;
    String optional_product_ids;
    String supplier_taxes_id;
    String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription_sale() {
        return description_sale;
    }

    public void setDescription_sale(String description_sale) {
        this.description_sale = description_sale;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDefault_code() {
        return default_code;
    }

    public void setDefault_code(String default_code) {
        this.default_code = default_code;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getCateg_id() {
        return categ_id;
    }

    public void setCateg_id(String categ_id) {
        this.categ_id = categ_id;
    }

    public String getLst_price() {
        return lst_price;
    }

    public void setLst_price(String lst_price) {
        this.lst_price = lst_price;
    }

    public String getStandard_price() {
        return standard_price;
    }

    public void setStandard_price(String standard_price) {
        this.standard_price = standard_price;
    }

    public String getSales_count() {
        return sales_count;
    }

    public void setSales_count(String sales_count) {
        this.sales_count = sales_count;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getInvoice_policy() {
        return invoice_policy;
    }

    public void setInvoice_policy(String invoice_policy) {
        this.invoice_policy = invoice_policy;
    }

    public String getExpense_policy() {
        return expense_policy;
    }

    public void setExpense_policy(String expense_policy) {
        this.expense_policy = expense_policy;
    }

    public String getOptional_product_ids() {
        return optional_product_ids;
    }

    public void setOptional_product_ids(String optional_product_ids) {
        this.optional_product_ids = optional_product_ids;
    }

    public String getSupplier_taxes_id() {
        return supplier_taxes_id;
    }

    public void setSupplier_taxes_id(String supplier_taxes_id) {
        this.supplier_taxes_id = supplier_taxes_id;
    }

    public ProductModel() {

    }
}
