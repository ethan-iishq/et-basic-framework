package ethan.etframework.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ethanx
 * @since 2017-12-11
 */
@TableName("recv_file")
public class RecvFile extends Model<RecvFile> {

    private static final long serialVersionUID = 1L;

	@TableId(value="ID", type= IdType.AUTO)
	private Integer id;
	private String filename;
	private String filepath;
    /**
     * 0开始接收，1接收完毕
     */
	private Integer status;
	@TableField("recv_percent")
	private Integer recvPercent;
	@TableField("create_time")
	private Date createTime;
	@TableField("update_time")
	private Date updateTime;
	private String remark;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getRecvPercent() {
		return recvPercent;
	}

	public void setRecvPercent(Integer recvPercent) {
		this.recvPercent = recvPercent;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "RecvFile{" +
			", id=" + id +
			", filename=" + filename +
			", filepath=" + filepath +
			", status=" + status +
			", recvPercent=" + recvPercent +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			", remark=" + remark +
			"}";
	}
}
