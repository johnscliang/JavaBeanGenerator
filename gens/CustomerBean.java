package cn.cnicg.bean;

import java.io.Serializable;

public class CustomerBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String code;
    private String name;
    private String address;
    private String map_flag;
    private String default_stores;
    private String phone;
    private String type;
    private String merchandiser;
    private String original_way;
    private String floor;
    private String referrer;
    private String payment_method;
    private String order_habit;
    private String used_address;
    private String remark;

    //getter
    public int getId() { 
        return id;
    }
    public String getCode() { 
        return code;
    }
    public String getName() { 
        return name;
    }
    public String getAddress() { 
        return address;
    }
    public String getMap_flag() { 
        return map_flag;
    }
    public String getDefault_stores() { 
        return default_stores;
    }
    public String getPhone() { 
        return phone;
    }
    public String getType() { 
        return type;
    }
    public String getMerchandiser() { 
        return merchandiser;
    }
    public String getOriginal_way() { 
        return original_way;
    }
    public String getFloor() { 
        return floor;
    }
    public String getReferrer() { 
        return referrer;
    }
    public String getPayment_method() { 
        return payment_method;
    }
    public String getOrder_habit() { 
        return order_habit;
    }
    public String getUsed_address() { 
        return used_address;
    }
    public String getRemark() { 
        return remark;
    }

    //setter
    public void setId(int id) { 
        this.id = id;
    }
    public void setCode(String code) { 
        this.code = code;
    }
    public void setName(String name) { 
        this.name = name;
    }
    public void setAddress(String address) { 
        this.address = address;
    }
    public void setMap_flag(String map_flag) { 
        this.map_flag = map_flag;
    }
    public void setDefault_stores(String default_stores) { 
        this.default_stores = default_stores;
    }
    public void setPhone(String phone) { 
        this.phone = phone;
    }
    public void setType(String type) { 
        this.type = type;
    }
    public void setMerchandiser(String merchandiser) { 
        this.merchandiser = merchandiser;
    }
    public void setOriginal_way(String original_way) { 
        this.original_way = original_way;
    }
    public void setFloor(String floor) { 
        this.floor = floor;
    }
    public void setReferrer(String referrer) { 
        this.referrer = referrer;
    }
    public void setPayment_method(String payment_method) { 
        this.payment_method = payment_method;
    }
    public void setOrder_habit(String order_habit) { 
        this.order_habit = order_habit;
    }
    public void setUsed_address(String used_address) { 
        this.used_address = used_address;
    }
    public void setRemark(String remark) { 
        this.remark = remark;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", address=").append(address);
        sb.append(", map_flag=").append(map_flag);
        sb.append(", default_stores=").append(default_stores);
        sb.append(", phone=").append(phone);
        sb.append(", type=").append(type);
        sb.append(", merchandiser=").append(merchandiser);
        sb.append(", original_way=").append(original_way);
        sb.append(", floor=").append(floor);
        sb.append(", referrer=").append(referrer);
        sb.append(", payment_method=").append(payment_method);
        sb.append(", order_habit=").append(order_habit);
        sb.append(", used_address=").append(used_address);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

}