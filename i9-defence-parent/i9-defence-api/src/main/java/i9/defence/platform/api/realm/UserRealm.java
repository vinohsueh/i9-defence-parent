package i9.defence.platform.api.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/** 
 * 创建时间：2017年11月15日 上午9:39:37
 * @author  lby
 * @version  
 * 
 */
public class UserRealm extends AuthorizingRealm{
    
    /*@Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;*/
    
    
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        /*String currentLoginName = (String)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = userService.findUserByName(currentLoginName);
        if (user != null) {
            List<Role> roles = roleService.getRolesByUser(user.getId());
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
            authorizationInfo.setStringPermissions(permissionNames);
        }else{
            throw new AuthorizationException();  
        }
        return authorizationInfo;*/
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        /*String username = (String) authcToken.getPrincipal();
        User user = userService.findUserByName(username);
        if (user == null) {
            throw new UnknownAccountException();
        }
        if (user.getStatus() == 1) {
            // 用户被管理员锁定抛出异常
            throw new LockedAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUsername(), user.getPassword(), getName());
        return authenticationInfo;*/
        return null;
    }

}
