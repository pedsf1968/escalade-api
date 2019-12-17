package com.dsf.escalade.web.controller;

import com.dsf.escalade.model.business.StatusType;
import com.dsf.escalade.service.*;
import com.dsf.escalade.web.controller.path.PathTable;
import com.dsf.escalade.web.dto.*;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Controller
@Slf4j
public class TopoController {

    @PersistenceContext
    private final EntityManager entityManager;
    private final UserService userService;
    private final AddressService addressService;
    private final TopoService topoService;
    private final SectorService sectorService;
    private final CommentService commentService;
    private final TagService tagService;
    private final  List<String> statusList =  Stream.of(StatusType.values()).map(Enum::name).collect(Collectors.toList());

    @Autowired
    public TopoController(EntityManager entityManager, UserService userService, AddressService addressService, TopoService topoService, SectorService sectorService, CommentService commentService, TagService tagService) {
        this.entityManager = entityManager;
        this.userService = userService;
        this.addressService = addressService;
        this.topoService = topoService;
        this.sectorService = sectorService;
        this.commentService = commentService;
        this.tagService = tagService;
    }

    @GetMapping("/topo/all")
    public String listTopo(Model model) {
        List<TopoDto> topoDtoList = topoService.findAll();
        Map<TopoDto,List<TagDto>> tagsByTopoId = new HashMap<>();

        for(TopoDto topoDto: topoDtoList) {
            tagsByTopoId.put(topoDto, tagService.findByTopoId(topoDto.getId()) );
        }

        model.addAttribute(PathTable.ATTRIBUTE_TOPO_LIST, topoDtoList);
        model.addAttribute(PathTable.ATTRIBUTE_TAGS, tagsByTopoId);
        return PathTable.TOPO_ALL;
    }

    @GetMapping("/topo/mylist")
    public String myListTopo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<TopoDto> topoDtoList = topoService.findByManagerId(userService.findByEmail(authentication.getName()).getId());
        Map<TopoDto,List<TagDto>> tagsByTopoId = new HashMap<>();

        for(TopoDto topoDto: topoDtoList) {
            tagsByTopoId.put(topoDto, tagService.findByTopoId(topoDto.getId()) );
        }

        model.addAttribute(PathTable.ATTRIBUTE_TOPO_LIST, topoDtoList);
        model.addAttribute(PathTable.ATTRIBUTE_TAGS, tagsByTopoId);
        return PathTable.TOPO_MYLIST;
    }

    @GetMapping("/topo/new")
    public String newTopo( Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        TopoDto topoDto = new TopoDto();
        AddressDto addressDto = new AddressDto();

        topoDto.setAliasManager(userService.findByEmail(authentication.getName()).getAlias());
        topoDto.setDate(Date.valueOf(LocalDate.now()));
        model.addAttribute(PathTable.ATTRIBUTE_TOPO, topoDto);
        model.addAttribute(PathTable.ATTRIBUTE_ADDRESS, addressDto);
        model.addAttribute(PathTable.ATTRIBUTE_STATUS_LIST, statusList);

        return PathTable.TOPO_ADD;
    }

    @PostMapping("/topo/add")
    public String addTopo(@ModelAttribute("topoDto") @Valid TopoDto topoDto, @NotNull  BindingResult bindingResultTopo,
                          @ModelAttribute("addressDto") @Valid AddressDto addressDto, @NonNull BindingResult bindingResultAddress, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (bindingResultTopo.hasErrors() || bindingResultAddress.hasErrors()) {
            model.addAttribute(PathTable.ATTRIBUTE_STATUS_LIST, statusList);
            return PathTable.TOPO_ADD;
        }

        // verify that the manager is the Topo manager
        if(userService.findByAlias(topoDto.getAliasManager()).getEmail().equals(authentication.getName())) {
            topoDto.setAddressId(addressService.save(addressDto));
            topoService.save(topoDto);
        }

        List<TopoDto> topoDtoList = topoService.findByManagerId(userService.findByEmail(authentication.getName()).getId());
        model.addAttribute(PathTable.ATTRIBUTE_TOPO_LIST, topoDtoList);
        return PathTable.TOPO_MYLIST_R;
    }

    @GetMapping("/topo/read/{id}")
    public String readTopo(@PathVariable("id") Integer id, Model model) {
        TopoDto topoDto = topoService.getOne(id);
        List<SectorDto> sectorDtoList = sectorService.findByTopoId(id);

        model.addAttribute(PathTable.ATTRIBUTE_TOPO, topoDto);
        model.addAttribute(PathTable.ATTRIBUTE_SECTOR_LIST, sectorDtoList);
        model.addAttribute(PathTable.ATTRIBUTE_ADDRESS,addressService.getOne(topoDto.getAddressId()));
        model.addAttribute(PathTable.ATTRIBUTE_COMMENT_LIST, commentService.getBySiteId(id));
        model.addAttribute("tags", tagService.findByTopoId(id));

        return PathTable.TOPO_READ;
    }

    @GetMapping("/topo/edit/{id}")
    public String editTopo(@PathVariable("id") Integer id, Model model) {
        TopoDto topoDto = topoService.getOne(id);
        List<SectorDto> sectorDtoList = sectorService.findByTopoId(id);

        model.addAttribute(PathTable.ATTRIBUTE_TOPO, topoDto);
        model.addAttribute(PathTable.ATTRIBUTE_SECTOR_LIST, sectorDtoList);
        model.addAttribute(PathTable.ATTRIBUTE_ADDRESS,addressService.getOne(topoDto.getAddressId()));
        model.addAttribute(PathTable.ATTRIBUTE_STATUS_LIST, statusList);

        return PathTable.TOPO_UPDATE;
    }

    @PostMapping("/topo/update/{id}")
    public String updateTopo(@PathVariable("id") Integer id, @ModelAttribute("topoDto") @Valid TopoDto topoDto, @NotNull  BindingResult bindingResultTopo,
                             @ModelAttribute("addressDto") @Valid AddressDto addressDto, @NonNull BindingResult bindingResultAddress, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(bindingResultTopo.hasErrors() || bindingResultAddress.hasErrors()){
            List<SectorDto> sectorDtoList = sectorService.findByTopoId(id);
            model.addAttribute(PathTable.ATTRIBUTE_SECTOR_LIST, sectorDtoList);
            model.addAttribute(PathTable.ATTRIBUTE_ADDRESS,addressService.getOne(topoDto.getAddressId()));
            model.addAttribute(PathTable.ATTRIBUTE_STATUS_LIST, statusList);
            return PathTable.TOPO_UPDATE;
        }

        // verify that the manager is the Topo manager
        if(userService.findByAlias(topoDto.getAliasManager()).getEmail().equals(authentication.getName())) {
            topoDto.setAddressId(addressService.save(addressDto));
            topoService.save(topoDto);
        }

        List<TopoDto> topoDtoList = topoService.findByManagerId(userService.findByEmail(authentication.getName()).getId());
        model.addAttribute(PathTable.ATTRIBUTE_TOPO_LIST, topoDtoList);

        return PathTable.TOPO_MYLIST_R;
    }

    @GetMapping("/topo/delete/{id}")
    public String deleteTopo(@PathVariable("id") Integer id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        TopoDto topoDto = topoService.getOne(id);

        // verify that the manager is the Topo manager
        if(userService.findByAlias(topoDto.getAliasManager()).getEmail().equals(authentication.getName())){
            topoService.delete(topoDto);
        }

        List<TopoDto> topoDtoList = topoService.findByManagerId(userService.findByEmail(authentication.getName()).getId());
        model.addAttribute(PathTable.ATTRIBUTE_TOPO_LIST, topoDtoList);

        return PathTable.TOPO_MYLIST_R;
    }

    @PostMapping("/topo/comment/{topoId}")
public String addTopoComment(@PathVariable("topoId") Integer topoId, @ModelAttribute("commentaire") String commentaire, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        TopoDto topoDto = topoService.getOne(topoId);
        List<SectorDto> sectorDtoList = sectorService.findByTopoId(topoId);

        //don't save anonymous comment
        if(!authentication.getName().equals("anonymousUser")) {
            CommentDto commentDto = new CommentDto();
            commentDto.setSiteId(topoId);
            commentDto.setText(commentaire);
            log.info("User : " + authentication.getName() + " add comment");
            commentDto.setAlias(userService.findByEmail(authentication.getName()).getAlias());
            commentService.save(commentDto);
        }

        model.addAttribute(PathTable.ATTRIBUTE_TOPO, topoDto);
        model.addAttribute(PathTable.ATTRIBUTE_SECTOR_LIST, sectorDtoList);
        model.addAttribute(PathTable.ATTRIBUTE_ADDRESS,addressService.getOne(topoDto.getAddressId()));
        model.addAttribute(PathTable.ATTRIBUTE_COMMENT_LIST, commentService.getBySiteId(topoId));
        model.addAttribute("tags", tagService.findByTopoId(topoId));

        return PathTable.TOPO_READ_R + topoId;
    }

    @GetMapping("/topo/comment/delete/{id}")
    public String deleteTopoComment(@PathVariable("id") Integer id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CommentDto commentDto = commentService.getOne(id);
        Integer siteId = commentDto.getSiteId();
        TopoDto topoDto = topoService.getOne(siteId);
        List<SectorDto> sectorDtoList = sectorService.findByTopoId(siteId);

        // verify that the manager is the Topo manager
        if( authentication.getAuthorities().stream()
              .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))){
            commentService.delete(commentDto);
        }

        model.addAttribute(PathTable.ATTRIBUTE_TOPO, topoDto);
        model.addAttribute(PathTable.ATTRIBUTE_SECTOR_LIST, sectorDtoList);
        model.addAttribute(PathTable.ATTRIBUTE_ADDRESS,addressService.getOne(topoDto.getAddressId()));
        model.addAttribute(PathTable.ATTRIBUTE_COMMENT_LIST, commentService.getBySiteId(siteId));
        model.addAttribute("tags", tagService.findByTopoId(id));

        return PathTable.TOPO_READ_R + siteId;
    }

    @PostMapping("/topo/tag/update/{id}")
    public String addTopoTag(@PathVariable("id") Integer id, @RequestParam(value="taglist", required = false) Integer[] tags, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        TopoDto topoDto = topoService.getOne(id);
        List<TagDto> tagDtos = tagService.findAll();

        if(tags!=null){
            for (int i = 0; i < tags.length; i++) {
                tagDtos.get(tags[i]-1).setActivated(Boolean.TRUE);
            }
        }
        // verify that the manager is the Topo manager
        if( authentication.getAuthorities().stream()
              .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))){
            tagService.update(id,tagDtos);
        }

        model.addAttribute(PathTable.ATTRIBUTE_TOPO, topoDto);
        model.addAttribute(PathTable.ATTRIBUTE_SECTOR_LIST, sectorService.findByTopoId(id));
        model.addAttribute(PathTable.ATTRIBUTE_ADDRESS,addressService.getOne(topoDto.getAddressId()));
        model.addAttribute(PathTable.ATTRIBUTE_COMMENT_LIST, commentService.getBySiteId(id));
        model.addAttribute("tags", tagDtos);

        return PathTable.TOPO_READ_R + id;
    }

}
