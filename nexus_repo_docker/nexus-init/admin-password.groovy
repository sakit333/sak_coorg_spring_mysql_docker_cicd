import org.sonatype.nexus.security.user.UserManager
import org.sonatype.nexus.security.user.UserSearchCriteria

def userManager = container.lookup(UserManager.NAME)

def adminUser = userManager.getUser("admin")
adminUser.setPassword("admin")
userManager.updateUser(adminUser)
