package com.example.nandivishal;

public class UserHelperClass {
    String cust_code,cust_name , cust_region, cust_sls_psn, cust_type,cust_tax,Id;

    public UserHelperClass() {
    }

    public UserHelperClass(String cust_code, String cust_name, String cust_region, String cust_sls_psn, String cust_type, String cust_tax) {
        this.cust_code = cust_code;
        this.cust_name = cust_name;
        this.cust_region = cust_region;
        this.cust_sls_psn = cust_sls_psn;
        this.cust_type = cust_type;
        this.cust_tax = cust_tax;
    }

    public String getCust_code() {
        return cust_code;
    }

    public void setCust_code(String cust_code) {
        this.cust_code = cust_code;
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public String getCust_region() {
        return cust_region;
    }

    public void setCust_region(String cust_region) {
        this.cust_region = cust_region;
    }

    public String getCust_sls_psn() {
        return cust_sls_psn;
    }

    public void setCust_sls_psn(String cust_sls_psn) {
        this.cust_sls_psn = cust_sls_psn;
    }

    public String getCust_type() {
        return cust_type;
    }

    public void setCust_type(String cust_type) {
        this.cust_type = cust_type;
    }

    public String getCust_tax() {
        return cust_tax;
    }



}
