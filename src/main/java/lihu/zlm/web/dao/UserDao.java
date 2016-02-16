package lihu.zlm.web.dao;

import org.springframework.stereotype.Repository;

import lihu.zlm.web.model.User;

@Repository("userDao")
public class UserDao extends BaseDao {

	public User queryByLoginEmail(String loginEmail) {
		return (User) this.getSqlMapClientTemplate().queryForObject("User.queryByLoginEmail", loginEmail);
	}

	public void register(User user) {
		this.getSqlMapClientTemplate().update("User.register", user);
	}

	public void updateInfo(User user) {
		this.getSqlMapClientTemplate().update("User.updateInfo", user);
	}

	public User queryByOAuthOpenid(String openid) {
		return (User) this.getSqlMapClientTemplate().queryForObject("User.queryByOAuthOpenid", openid);
	}

	public User queryByUserid(String userid) {
		return (User) this.getSqlMapClientTemplate().queryForObject("User.queryByUserid", userid);
	}

	public void changePassword(User user) {
		this.getSqlMapClientTemplate().update("User.changePassword", user);
	}
	
}
