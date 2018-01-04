package i9.defence.platform.api.realm;

import i9.defence.platform.model.Manager;
import i9.defence.platform.service.ManagerService;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/** 
 * 创建时间：2017年11月15日 上午9:39:37
 * @author  lby
 * @version  
 * 
 */
public class UserRealm extends AuthorizingRealm{
    
    @Autowired
    private ManagerService managerService;
    /* @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;*/
    
    
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String currentLoginName = (String)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Manager manager = managerService.getManagerByUsername(currentLoginName);
        if (manager != null) {
            /*List<Role> roles = roleService.getRolesByUser(user.getId());
            Set<String> roleNames = new HashSet<String>();
            for (Role role : roles) {
                roleNames.add(role.getCode());
            }
            // 将角色名称提供给info
            authorizationInfo.setRoles(roleNames);
            
            Set<Permission> permissions = permissionService.getPermissionByUserId(user.getId());
            Set<String> permissionNames = new HashSet<String>();
            for (Permission permission : permissions) {
                permissionNames.add(permission.getCode());
            }
            // 将权限名称提供给info
            authorizationInfo.setStringPermissions(permissionNames);*/
        }else{
            throw new AuthorizationException();  
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        String username = (String) authcToken.getPrincipal();
        Manager manager = managerService.getManagerByUsername(username);
        if (manager == null) {
            throw new UnknownAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                manager.getUsername(), manager.getPassword(), getName());
        return authenticationInfo;
    }

}
