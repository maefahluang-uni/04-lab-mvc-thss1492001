package th.mfu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ConcertController {
    private int nextId=1;
    // TODO: create hashmap of concerts for storing data
        HashMap<Integer,Concert> concertMap = new HashMap<>(5);
    //TODO: add initbinder to convert date
@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.US);
       // dateFormat.setLenient(false); // Disable lenient date parsing

        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @GetMapping("/concerts")
    public String listConcerts(Model model) {
        // TODO: add concerts to model
        List <Concert> concerts = new ArrayList<>(concertMap.values());
        //Concert concert = new Concert();
        model.addAttribute("concerts",concerts);
        // TODO: return a template to list concerts
        return "list-concert";
    }

    @GetMapping("/add-concert")
    public String addAConcertForm(Model model) {
        // TODO: pass blank concert to a form
       // Concert concert = new Concert();
       model.addAttribute("concerts",new Concert());
        // TODO: return a template for concert form
        return "add-concert-form";
    }

    @PostMapping("/concerts")
    public String saveConcert(@ModelAttribute Concert concert) {
        
        // TODO: add concert to list of concerts
        int id = nextId++;
        concert.setId(id);
        concertMap.put(id,concert);
        // TODO: increment nextId
      //  nextId++;
        // TODO: redirect to list concerts
        return "redirect:/concerts";
    }

    @GetMapping("/delete-concert/{id}")
    public String deleteConcert(@PathVariable int id) {
        // TODO: remove concert from list of concerts
        concertMap.remove(id);
    
        // TODO: redirect to list concerts
        return "redirect:/concerts";
    }

    
    @GetMapping("/delete-concert")
    public String removeAllConcerts() {
        //TODO: clear all employees and reset id
        concertMap.clear();
        nextId = 1;
        // TODO: redirect to list concerts
        return "redirect:/concerts";
    }

}
