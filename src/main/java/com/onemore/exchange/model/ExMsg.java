package com.onemore.exchange.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ExMsg implements Serializable {
    private Integer id;

    private String openid;

    private String avatarurl;

    private String nickname;

    private String msg;

    private String fromcurrency;

    private String tocurrency;

    private BigDecimal raterange;

    private Integer deleted;

    private Date createtime;

    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAvatarurl() {
        return avatarurl;
    }

    public void setAvatarurl(String avatarurl) {
        this.avatarurl = avatarurl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFromcurrency() {
        return fromcurrency;
    }

    public void setFromcurrency(String fromcurrency) {
        this.fromcurrency = fromcurrency;
    }

    public String getTocurrency() {
        return tocurrency;
    }

    public void setTocurrency(String tocurrency) {
        this.tocurrency = tocurrency;
    }

    public BigDecimal getRaterange() {
        return raterange;
    }

    public void setRaterange(BigDecimal raterange) {
        this.raterange = raterange;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", openid=").append(openid);
        sb.append(", avatarurl=").append(avatarurl);
        sb.append(", nickname=").append(nickname);
        sb.append(", msg=").append(msg);
        sb.append(", fromcurrency=").append(fromcurrency);
        sb.append(", tocurrency=").append(tocurrency);
        sb.append(", raterange=").append(raterange);
        sb.append(", deleted=").append(deleted);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}