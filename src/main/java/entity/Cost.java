package entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * JDBC֧�ֵ�Java����������java.sql���µ����ͣ�
 * 	Date ������
 * 	Time ʱ����
 * 	Timestamp ������ʱ����
 * ���Ƕ��̳���java.util.Date
 */
public class Cost implements Serializable {

	private Integer costId;
	private String name;
	//����ʱ��
	private Integer baseDuration;
	//��������
	private Double baseCost;
	//��λ����
	private Double unitCost;
	//״̬��0-��ͨ��1-��ͣ��
	private String status;
	//����
	private String descr;
	//����ʱ��
	private Timestamp creatime;
	//��ͨʱ��
	private Timestamp startime;
	//�ʷ����ͣ�1-���£�2-�ײͣ�3-��ʱ��
	private String costType;
	
	public Integer getCostId() {
		return costId;
	}
	public void setCostId(Integer costId) {
		this.costId = costId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getBaseDuration() {
		return baseDuration;
	}
	public void setBaseDuration(Integer baseDuration) {
		this.baseDuration = baseDuration;
	}
	public Double getBaseCost() {
		return baseCost;
	}
	public void setBaseCost(Double baseCost) {
		this.baseCost = baseCost;
	}
	public Double getUnitCost() {
		return unitCost;
	}
	public void setUnitCost(Double unitCost) {
		this.unitCost = unitCost;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public Timestamp getCreatime() {
		return creatime;
	}
	public void setCreatime(Timestamp creatime) {
		this.creatime = creatime;
	}
	public Timestamp getStartime() {
		return startime;
	}
	public void setStartime(Timestamp startime) {
		this.startime = startime;
	}
	public String getCostType() {
		return costType;
	}
	public void setCostType(String costType) {
		this.costType = costType;
	}
	
}





