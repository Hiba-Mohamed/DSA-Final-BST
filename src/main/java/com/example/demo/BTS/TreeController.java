package com.example.demo.BTS;

import com.example.demo.BTS.TreeRecord;
import com.example.demo.BTS.TreeRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@Controller
public class TreeController {

    @Autowired
    private TreeRecordRepository treeRecordRepository;

    private BinarySearchTree bst = new BinarySearchTree();

    @GetMapping("/enter-numbers")
    public String showInputForm() {
        return "enter-numbers";
    }

    @PostMapping("/process-numbers")
    public String processNumbers(@RequestParam("numbers") String numbers, Model model) {
        String[] numArray = numbers.split(",");
        List<Integer> numList = new ArrayList<>();

        for (String num : numArray) {
            numList.add(Integer.parseInt(num.trim()));
            bst.insert(Integer.parseInt(num.trim()));
        }

        TreeRecord treeRecord = new TreeRecord();
        treeRecord.setInputNumbers(numbers);
        treeRecord.setTreeStructure(bst.inorderTraversal().toString());
        treeRecordRepository.save(treeRecord);

        model.addAttribute("treeStructure", bst.inorderTraversal());
        return "tree-result";
    }

    @GetMapping("/previous-trees")
    public String showPreviousTrees(Model model) {
        List<TreeRecord> records = treeRecordRepository.findAll();
        model.addAttribute("records", records);
        return "previous-trees"; }
}
