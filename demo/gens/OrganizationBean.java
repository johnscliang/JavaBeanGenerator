package cn.cnicg.bean;

import java.io.Serializable;

public class Sys_organizationBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String org_name;
    private String org_manager;
    private String description;
    private String org_leader;
    private String remark;
    private String extend;
    private int create_time;

    //getter
    public int getId() { 
        return id;
    }
    public String getOrg_name() { 
        return org_name;
    }
    public String getOrg_manager() { 
        return org_manager;
    }
    public String getDescription() { 
        return description;
    }
    public String getOrg_leader() { 
        return org_leader;
    }
    public String getRemark() { 
        return remark;
    }
    public String getExtend() { 
        return extend;
    }
    public int getCreate_time() { 
        return create_time;
    }

    //setter
    public void setId(int id) { 
        this.id = id;
    }
    public void setOrg_name(String org_name) { 
        this.org_name = org_name;
    }
    public void setOrg_manager(String org_manager) { 
        this.org_manager = org_manager;
    }
    public void setDescription(String description) { 
        this.description = description;
    }
    public void setOrg_leader(String org_leader) { 
        this.org_leader = org_leader;
    }
    public void setRemark(String remark) { 
        this.remark = remark;
    }
    public void setExtend(String extend) { 
        this.extend = extend;
    }
    public void setCreate_time(int create_time) { 
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", org_name=").append(org_name);
        sb.append(", org_manager=").append(org_manager);
        sb.append(", description=").append(description);
        sb.append(", org_leader=").append(org_leader);
        sb.append(", remark=").append(remark);
        sb.append(", extend=").append(extend);
        sb.append(", create_time=").append(create_time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

}