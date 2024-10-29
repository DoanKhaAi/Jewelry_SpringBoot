package javaweb.Controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javaweb.Entity.MyUser;
import javaweb.Entity.Role;
import javaweb.Service.MyUserService;
import javaweb.Service.RoleService;

@Controller
@RequestMapping("/admin/role")
public class RoleController {
	@Autowired
    private RoleService service;
	
	@Autowired
    private MyUserService serviceMyUser;

    @GetMapping("/list")
    public String list(Model model) {
        List<Role> listRoles = service.list();
        model.addAttribute("listRoles", listRoles);
        return "/role/listRole";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("role", new Role());
        model.addAttribute("pageTitle", "Thêm quyền mới");
        return "/role/formRole";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Role role = service.getById(id);
        model.addAttribute("role", role);
        model.addAttribute("pageTitle", "Chỉnh sửa quyền (ID: " + id + ")");
        return "/role/formRole";
    }

    @PostMapping("/save")
    public String save(Role role, RedirectAttributes ra) {
        service.save(role);
        ra.addFlashAttribute("message", "Lưu quyền thành công");
        return "redirect:/admin/role/list";
    }
    
    @GetMapping("/user/list")
    public String listUsers(Model model) {
        List<MyUser> users = serviceMyUser.findAllUsers();
        model.addAttribute("users", users);
        return "role/listRoleUser";
    }
    
    @GetMapping("/user/update/{userID}")
    public String showUpdateRolesForm(@PathVariable("userID") Long userID, Model model) {
        MyUser user = serviceMyUser.findById(userID);
        List<Role> allRoles = service.list();
        model.addAttribute("user", user);
        model.addAttribute("allRoles", allRoles);
        model.addAttribute("pageTitle", "Chỉnh sửa quyền của người dùng (ID: " +userID + ")");
        return "role/formRoleUser";
    }

    @PostMapping("/user/save")
    public String updateRoles(@RequestParam("userID") Long userID, @RequestParam("roles") Set<Long> roleIDs, RedirectAttributes ra) {
        serviceMyUser.updateUserRoles(userID, roleIDs);
        ra.addFlashAttribute("message", "Thay đổi quyền cho người dùng thành công");
        return "redirect:/admin/role/user/list";
    }

}
