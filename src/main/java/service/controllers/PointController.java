package service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import service.dao.PointDAO;
import service.models.Point;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/points")
public class PointController {

    @Autowired
    private PointDAO pointDAO;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("points", pointDAO.index());
        return "points/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("point", pointDAO.show(id));
        return "points/show";
    }

    @GetMapping("/new")
    public String newPoint(@ModelAttribute("point") Point point) {
//        model.addAttribute("point", new Point());
        return "points/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("point") @Valid Point point, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "points/new";

        pointDAO.save(point);
        return "redirect:/points";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("point", pointDAO.show(id));
        return "points/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("point") @Valid Point point, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "points/edit";

        pointDAO.update(id, point);
        return "redirect:/points";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        pointDAO.delete(id);
        return "redirect:/points";
    }
}