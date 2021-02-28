package com.example.dulha_jee.pojo;

public class ShirtRequestBody {
    private String quantity, collar, sleeves, shoulder, hip, abdomen, gudda, front, lengthMade , double_sew ,double_cuff_soft_bukram,double_kaj_link_style,cuff_width,coller_width_point,commando_silaye_color, kurta_type,shalwar,karigar,urgent_order_time,urgent_order_date,side_pocket_image,cuff_image,collar_image,customer_image, customer_name, order_number, mobile_number,  patti_ki_chorayi, order_date, order_date_most_urgent, remarks, is_shirt, make_coverpati_style, regular_polo_pati, simple_pati_style, not_regular_polo_pat, back_dart, american_style_round_deep, readymade_shirt_style_chakooti, customer_cloth, only_sewing, child_kurta_size, finished_adjust, special_customer_order, regular_customer_order, urgent_order, no_label, special_order, button_should_be_strong, shoulder_down, light_work_shoulder_down, full_shoulder_down, straight_shoulder, right_shoulder_down, left_shoulder_down, deep_body, altered_body, party_label, fancy_label;
    private String order_status = "آرڈر آگیا";

    public String getDouble_sew() {
        return double_sew;
    }

    public void setDouble_sew(String double_sew) {
        this.double_sew = double_sew;
    }

    public String getDouble_cuff_soft_bukram() {
        return double_cuff_soft_bukram;
    }

    public void setDouble_cuff_soft_bukram(String double_cuff_soft_bukram) {
        this.double_cuff_soft_bukram = double_cuff_soft_bukram;
    }

    public String getDouble_kaj_link_style() {
        return double_kaj_link_style;
    }

    public void setDouble_kaj_link_style(String double_kaj_link_style) {
        this.double_kaj_link_style = double_kaj_link_style;
    }

    public String getCuff_width() {
        return cuff_width;
    }

    public void setCuff_width(String cuff_width) {
        this.cuff_width = cuff_width;
    }

    public String getColler_width_point() {
        return coller_width_point;
    }

    public void setColler_width_point(String coller_width_point) {
        this.coller_width_point = coller_width_point;
    }

    public String getCommando_silaye_color() {
        return commando_silaye_color;
    }

    public void setCommando_silaye_color(String commando_silaye_color) {
        this.commando_silaye_color = commando_silaye_color;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getKurta_type() {
        return kurta_type;
    }

    public void setKurta_type(String kurta_type) {
        this.kurta_type = kurta_type;
    }

    public String getShalwar() {
        return shalwar;
    }

    public void setShalwar(String shalwar) {
        this.shalwar = shalwar;
    }

    public String getKarigar() {
        return karigar;
    }

    public void setKarigar(String karigar) {
        this.karigar = karigar;
    }

    public String getUrgent_order_time() {
        return urgent_order_time;
    }

    public void setUrgent_order_time(String urgent_order_time) {
        this.urgent_order_time = urgent_order_time;
    }

    public String getUrgent_order_date() {
        return urgent_order_date;
    }

    public void setUrgent_order_date(String urgent_order_date) {
        this.urgent_order_date = urgent_order_date;
    }

    public String getSide_pocket_image() {
        return side_pocket_image;
    }

    public void setSide_pocket_image(String side_pocket_image) {
        this.side_pocket_image = side_pocket_image;
    }

    public String getCuff_image() {
        return cuff_image;
    }

    public void setCuff_image(String cuff_image) {
        this.cuff_image = cuff_image;
    }

    public String getCollar_image() {
        return collar_image;
    }

    public void setCollar_image(String collar_image) {
        this.collar_image = collar_image;
    }

    public String getCustomer_image() {
        return customer_image;
    }

    public void setCustomer_image(String customer_image) {
        this.customer_image = customer_image;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public ShirtRequestBody(){

    }

    public ShirtRequestBody(String quantity, String collar, String sleeves, String shoulder, String hip, String abdomen, String gudda, String front, String lengthMade, String patti_ki_chorayi, String order_date, String order_date_most_urgent, String remarks, String is_shirt, String make_coverpati_style, String regular_polo_pati, String simple_pati_style, String not_regular_polo_pat, String back_dart, String american_style_round_deep, String readymade_shirt_style_chakooti, String customer_cloth, String only_sewing, String child_kurta_size, String finished_adjust, String special_customer_order, String regular_customer_order, String urgent_order, String no_label, String special_order, String button_should_be_strong, String shoulder_down, String light_work_shoulder_down, String full_shoulder_down, String straight_shoulder, String right_shoulder_down, String left_shoulder_down, String deep_body, String altered_body, String party_label, String fancy_label) {
        this.quantity = quantity;
        this.collar = collar;
        this.sleeves = sleeves;
        this.shoulder = shoulder;
        this.hip = hip;
        this.abdomen = abdomen;
        this.gudda = gudda;
        this.front = front;
        this.lengthMade = lengthMade;
        this.patti_ki_chorayi = patti_ki_chorayi;
        this.order_date = order_date;
        this.order_date_most_urgent = order_date_most_urgent;
        this.remarks = remarks;
        this.is_shirt = is_shirt;
        this.make_coverpati_style = make_coverpati_style;
        this.regular_polo_pati = regular_polo_pati;
        this.simple_pati_style = simple_pati_style;
        this.not_regular_polo_pat = not_regular_polo_pat;
        this.back_dart = back_dart;
        this.american_style_round_deep = american_style_round_deep;
        this.readymade_shirt_style_chakooti = readymade_shirt_style_chakooti;
        this.customer_cloth = customer_cloth;
        this.only_sewing = only_sewing;
        this.child_kurta_size = child_kurta_size;
        this.finished_adjust = finished_adjust;
        this.special_customer_order = special_customer_order;
        this.regular_customer_order = regular_customer_order;
        this.urgent_order = urgent_order;
        this.no_label = no_label;
        this.special_order = special_order;
        this.button_should_be_strong = button_should_be_strong;
        this.shoulder_down = shoulder_down;
        this.light_work_shoulder_down = light_work_shoulder_down;
        this.full_shoulder_down = full_shoulder_down;
        this.straight_shoulder = straight_shoulder;
        this.right_shoulder_down = right_shoulder_down;
        this.left_shoulder_down = left_shoulder_down;
        this.deep_body = deep_body;
        this.altered_body = altered_body;
        this.party_label = party_label;
        this.fancy_label = fancy_label;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCollar() {
        return collar;
    }

    public void setCollar(String collar) {
        this.collar = collar;
    }

    public String getSleeves() {
        return sleeves;
    }

    public void setSleeves(String sleeves) {
        this.sleeves = sleeves;
    }

    public String getShoulder() {
        return shoulder;
    }

    public void setShoulder(String shoulder) {
        this.shoulder = shoulder;
    }

    public String getHip() {
        return hip;
    }

    public void setHip(String hip) {
        this.hip = hip;
    }

    public String getAbdomen() {
        return abdomen;
    }

    public void setAbdomen(String abdomen) {
        this.abdomen = abdomen;
    }

    public String getGudda() {
        return gudda;
    }

    public void setGudda(String gudda) {
        this.gudda = gudda;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getLengthMade() {
        return lengthMade;
    }

    public void setLengthMade(String lengthMade) {
        this.lengthMade = lengthMade;
    }

    public String getPatti_ki_chorayi() {
        return patti_ki_chorayi;
    }

    public void setPatti_ki_chorayi(String patti_ki_chorayi) {
        this.patti_ki_chorayi = patti_ki_chorayi;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getOrder_date_most_urgent() {
        return order_date_most_urgent;
    }

    public void setOrder_date_most_urgent(String order_date_most_urgent) {
        this.order_date_most_urgent = order_date_most_urgent;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getIs_shirt() {
        return is_shirt;
    }

    public void setIs_shirt(String is_shirt) {
        this.is_shirt = is_shirt;
    }

    public String getMake_coverpati_style() {
        return make_coverpati_style;
    }

    public void setMake_coverpati_style(String make_coverpati_style) {
        this.make_coverpati_style = make_coverpati_style;
    }

    public String getRegular_polo_pati() {
        return regular_polo_pati;
    }

    public void setRegular_polo_pati(String regular_polo_pati) {
        this.regular_polo_pati = regular_polo_pati;
    }

    public String getSimple_pati_style() {
        return simple_pati_style;
    }

    public void setSimple_pati_style(String simple_pati_style) {
        this.simple_pati_style = simple_pati_style;
    }

    public String getNot_regular_polo_pat() {
        return not_regular_polo_pat;
    }

    public void setNot_regular_polo_pat(String not_regular_polo_pat) {
        this.not_regular_polo_pat = not_regular_polo_pat;
    }

    public String getBack_dart() {
        return back_dart;
    }

    public void setBack_dart(String back_dart) {
        this.back_dart = back_dart;
    }

    public String getAmerican_style_round_deep() {
        return american_style_round_deep;
    }

    public void setAmerican_style_round_deep(String american_style_round_deep) {
        this.american_style_round_deep = american_style_round_deep;
    }

    public String getReadymade_shirt_style_chakooti() {
        return readymade_shirt_style_chakooti;
    }

    public void setReadymade_shirt_style_chakooti(String readymade_shirt_style_chakooti) {
        this.readymade_shirt_style_chakooti = readymade_shirt_style_chakooti;
    }

    public String getCustomer_cloth() {
        return customer_cloth;
    }

    public void setCustomer_cloth(String customer_cloth) {
        this.customer_cloth = customer_cloth;
    }

    public String getOnly_sewing() {
        return only_sewing;
    }

    public void setOnly_sewing(String only_sewing) {
        this.only_sewing = only_sewing;
    }

    public String getChild_kurta_size() {
        return child_kurta_size;
    }

    public void setChild_kurta_size(String child_kurta_size) {
        this.child_kurta_size = child_kurta_size;
    }

    public String getFinished_adjust() {
        return finished_adjust;
    }

    public void setFinished_adjust(String finished_adjust) {
        this.finished_adjust = finished_adjust;
    }

    public String getSpecial_customer_order() {
        return special_customer_order;
    }

    public void setSpecial_customer_order(String special_customer_order) {
        this.special_customer_order = special_customer_order;
    }

    public String getRegular_customer_order() {
        return regular_customer_order;
    }

    public void setRegular_customer_order(String regular_customer_order) {
        this.regular_customer_order = regular_customer_order;
    }

    public String getUrgent_order() {
        return urgent_order;
    }

    public void setUrgent_order(String urgent_order) {
        this.urgent_order = urgent_order;
    }

    public String getNo_label() {
        return no_label;
    }

    public void setNo_label(String no_label) {
        this.no_label = no_label;
    }

    public String getSpecial_order() {
        return special_order;
    }

    public void setSpecial_order(String special_order) {
        this.special_order = special_order;
    }

    public String getButton_should_be_strong() {
        return button_should_be_strong;
    }

    public void setButton_should_be_strong(String button_should_be_strong) {
        this.button_should_be_strong = button_should_be_strong;
    }

    public String getShoulder_down() {
        return shoulder_down;
    }

    public void setShoulder_down(String shoulder_down) {
        this.shoulder_down = shoulder_down;
    }

    public String getLight_work_shoulder_down() {
        return light_work_shoulder_down;
    }

    public void setLight_work_shoulder_down(String light_work_shoulder_down) {
        this.light_work_shoulder_down = light_work_shoulder_down;
    }

    public String getFull_shoulder_down() {
        return full_shoulder_down;
    }

    public void setFull_shoulder_down(String full_shoulder_down) {
        this.full_shoulder_down = full_shoulder_down;
    }

    public String getStraight_shoulder() {
        return straight_shoulder;
    }

    public void setStraight_shoulder(String straight_shoulder) {
        this.straight_shoulder = straight_shoulder;
    }

    public String getRight_shoulder_down() {
        return right_shoulder_down;
    }

    public void setRight_shoulder_down(String right_shoulder_down) {
        this.right_shoulder_down = right_shoulder_down;
    }

    public String getLeft_shoulder_down() {
        return left_shoulder_down;
    }

    public void setLeft_shoulder_down(String left_shoulder_down) {
        this.left_shoulder_down = left_shoulder_down;
    }

    public String getDeep_body() {
        return deep_body;
    }

    public void setDeep_body(String deep_body) {
        this.deep_body = deep_body;
    }

    public String getAltered_body() {
        return altered_body;
    }

    public void setAltered_body(String altered_body) {
        this.altered_body = altered_body;
    }

    public String getParty_label() {
        return party_label;
    }

    public void setParty_label(String party_label) {
        this.party_label = party_label;
    }

    public String getFancy_label() {
        return fancy_label;
    }

    public void setFancy_label(String fancy_label) {
        this.fancy_label = fancy_label;
    }
}
