/**
 * 
 */
package top.anets.entity;

import java.io.Serializable;
import java.util.Date;



import lombok.Data;

/**
 * @author Administrator
 *
 */
@Data
public class User implements Serializable{
		/**
	 * 
	 */
	private static final long serialVersionUID = 5164036926243154383L;
		private Integer id;
		private String username;
		private String password;
		private String salt;//盐值
		private String email;
		private String mobile;
		private Integer valid=1;
	    private Integer deptId;
		private Date createdTime;
		private Date modifiedTime;
		private String createdUser;
		private String modifiedUser;

}
