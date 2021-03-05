/**
 * 
 */
package top.anets.redis;

/**
 * @author Administrator
 *
 */
public enum RedisKey {
	USER("user:",0),
	ITEM("item:",30);

	private String prefix;
	private int seconds;
	
	
	RedisKey(String prefix, int seconds) {
		this.prefix=prefix;
		this.seconds=seconds;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public int getSeconds() {
		return seconds;
	}
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}
	
	
}
