package com.dsf.escalade.web.controller;

import com.dsf.escalade.model.business.StatusType;
import com.dsf.escalade.service.business.SectorService;
import com.dsf.escalade.service.business.TagService;
import com.dsf.escalade.service.business.TopoService;
import com.dsf.escalade.service.business.VoieService;
import com.dsf.escalade.service.global.AddressService;
import com.dsf.escalade.service.global.CommentService;
import com.dsf.escalade.service.global.UserService;
import com.dsf.escalade.web.controller.path.PathTable;
import com.dsf.escalade.web.dto.*;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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

@Slf4j
@Controller
@RefreshScope
public class TopoController {

    @PersistenceContext
    private final EntityManager entityManager;

    private final UserService userService;
    private final AddressService addressService;
    private final TopoService topoService;
    private final SectorService sectorService;
    private final VoieService voieService;
    private final TagService tagService;
    private final CommentService commentService;
    private final  List<String> statusList =  Stream.of(StatusType.values()).map(Enum::name).collect(Collectors.toList());

    @Autowired
    public TopoController(EntityManager entityManager, UserService userService, AddressService addressService, TopoService topoService, SectorService sectorService, VoieService voieService, TagService tagService, CommentService commentService) {
        this.entityManager = entityManager;
        this.userService = userService;
        this.addressService = addressService;
        this.topoService = topoService;
        this.sectorService = sectorService;
        this.voieService = voieService;
        this.tagService = tagService;
        this.commentService = commentService;
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
        model.addAttribute(PathTable.ATTRIBUTE_FILTER, new TopoDto());
        model.addAttribute(PathTable.ATTRIBUTE_STATUS_LIST, statusList);
        model.addAttribute(PathTable.ATTRIBUTE_REGION_LIST, topoService.findAllRegion());
        model.addAttribute(PathTable.ATTRIBUTE_ALIAS_LIST, topoService.findAllAlias());

        return PathTable.TOPO_ALL;
    }

    @PostMapping("/topo/all")
    public String listTopoFiltered(@ModelAttribute("filter") TopoDto filter, Model model) {
        List<TopoDto> topoDtoList = topoDtoList = topoService.findAllFiltered(filter);


        Map<TopoDto,List<TagDto>> tagsByTopoId = new HashMap<>();

        for(TopoDto topoDto: topoDtoList) {
            tagsByTopoId.put(topoDto, tagService.findByTopoId(topoDto.getId()) );
        }

        model.addAttribute(PathTable.ATTRIBUTE_TOPO_LIST, topoDtoList);
        model.addAttribute(PathTable.ATTRIBUTE_TAGS, tagsByTopoId);
        model.addAttribute(PathTable.ATTRIBUTE_FILTER, filter);
        model.addAttribute(PathTable.ATTRIBUTE_STATUS_LIST, statusList);
        model.addAttribute(PathTable.ATTRIBUTE_REGION_LIST, topoService.findAllRegion());
        model.addAttribute(PathTable.ATTRIBUTE_ALIAS_LIST, topoService.findAllAlias());

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
    public String readTopo(@PathVariable("id") Integer topoId, Model model) {
        TopoDto topoDto = topoService.getOne(topoId);

        model.addAttribute(PathTable.ATTRIBUTE_TOPO, topoDto);
        model.addAttribute(PathTable.ATTRIBUTE_SECTOR_LIST, sectorService.findByTopoId(topoId));
        model.addAttribute(PathTable.ATTRIBUTE_VOIE_LIST, voieService.findByParentId(topoId));
        model.addAttribute(PathTable.ATTRIBUTE_ADDRESS,addressService.getOne(topoDto.getAddressId()));
        model.addAttribute(PathTable.ATTRIBUTE_COMMENT_LIST, commentService.getBySiteId(topoId));
        model.addAttribute("tags", tagService.findByTopoId(topoId));

        return PathTable.TOPO_READ;
    }

    @GetMapping("/topo/edit/{id}")
    public String editTopo(@PathVariable("id") Integer topoId, Model model) {
        TopoDto topoDto = topoService.getOne(topoId);

        model.addAttribute(PathTable.ATTRIBUTE_TOPO, topoDto);
        model.addAttribute(PathTable.ATTRIBUTE_SECTOR_LIST, sectorService.findByTopoId(topoId));
        model.addAttribute(PathTable.ATTRIBUTE_VOIE_LIST, voieService.findByParentId(topoId));
        model.addAttribute(PathTable.ATTRIBUTE_ADDRESS,addressService.getOne(topoDto.getAddressId()));
        model.addAttribute(PathTable.ATTRIBUTE_STATUS_LIST, statusList);

        return PathTable.TOPO_UPDATE;
    }

    @PostMapping("/topo/update/{id}")
    public String updateTopo(@PathVariable("id") Integer topoId, @ModelAttribute("topoDto") @Valid TopoDto topoDto, @NotNull  BindingResult bindingResultTopo,
                             @ModelAttribute("addressDto") @Valid AddressDto addressDto, @NonNull BindingResult bindingResultAddress, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(bindingResultTopo.hasErrors() || bindingResultAddress.hasErrors()){
            model.addAttribute(PathTable.ATTRIBUTE_SECTOR_LIST, sectorService.findByTopoId(topoId));
            model.addAttribute(PathTable.ATTRIBUTE_VOIE_LIST, voieService.findByParentId(topoId));
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
        model.addAttribute(PathTable.ATTRIBUTE_VOIE_LIST, voieService.findByParentId(topoId));
        model.addAttribute(PathTable.ATTRIBUTE_ADDRESS,addressService.getOne(topoDto.getAddressId()));
        model.addAttribute(PathTable.ATTRIBUTE_COMMENT_LIST, commentService.getBySiteId(topoId));
        model.addAttribute("tags", tagService.findByTopoId(topoId));

        return PathTable.TOPO_READ_R + topoId;
    }

    @GetMapping("/topo/comment/delete/{id}")
    public String deleteTopoComment(@PathVariable("id") Integer topoId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CommentDto commentDto = commentService.getOne(topoId);
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
        model.addAttribute(PathTable.ATTRIBUTE_VOIE_LIST, voieService.findByParentId(topoId));
        model.addAttribute(PathTable.ATTRIBUTE_ADDRESS,addressService.getOne(topoDto.getAddressId()));
        model.addAttribute(PathTable.ATTRIBUTE_COMMENT_LIST, commentService.getBySiteId(siteId));
        model.addAttribute("tags", tagService.findByTopoId(topoId));

        return PathTable.TOPO_READ_R + siteId;
    }

    @PostMapping("/topo/tag/update/{id}")
    public String addTopoTag(@PathVariable("id") Integer topoId, @RequestParam(value="taglist", required = false) Integer[] tags, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        TopoDto topoDto = topoService.getOne(topoId);
        List<TagDto> tagDtos = tagService.findAll();

        if(tags!=null){
            for (int i = 0; i < tags.length; i++) {
                tagDtos.get(tags[i]-1).setActivated(Boolean.TRUE);
            }
        }
        // verify that the manager is the Topo manager
        if( authentication.getAuthorities().stream()
              .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))){
            tagService.update(topoId,tagDtos);
        }

        model.addAttribute(PathTable.ATTRIBUTE_TOPO, topoDto);
        model.addAttribute(PathTable.ATTRIBUTE_SECTOR_LIST, sectorService.findByTopoId(topoId));
        model.addAttribute(PathTable.ATTRIBUTE_VOIE_LIST, voieService.findByParentId(topoId));
        model.addAttribute(PathTable.ATTRIBUTE_ADDRESS,addressService.getOne(topoDto.getAddressId()));
        model.addAttribute(PathTable.ATTRIBUTE_COMMENT_LIST, commentService.getBySiteId(topoId));
        model.addAttribute("tags", tagDtos);

        return PathTable.TOPO_READ_R + topoId;
    }

}
