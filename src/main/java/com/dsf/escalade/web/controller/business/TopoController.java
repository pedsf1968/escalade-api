package com.dsf.escalade.web.controller.business;

import com.dsf.escalade.model.business.StatusType;
import com.dsf.escalade.service.business.*;
import com.dsf.escalade.service.global.AddressService;
import com.dsf.escalade.service.global.CommentService;
import com.dsf.escalade.service.global.UserService;
import com.dsf.escalade.web.controller.path.PathTable;
import com.dsf.escalade.web.dto.AddressDto;
import com.dsf.escalade.web.dto.TagDto;
import com.dsf.escalade.web.dto.TopoDto;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    private final UserService userService;
    private final AddressService addressService;
    private final TopoService topoService;
    private final SectorService sectorService;
    private final VoieService voieService;
    private final TagService tagService;
    private final CommentService commentService;
    private final CotationService cotationService;
    private final  List<String> statusList =  Stream.of(StatusType.values()).map(Enum::name).collect(Collectors.toList());

    @Autowired
    public TopoController(UserService userService, AddressService addressService, TopoService topoService, SectorService sectorService, VoieService voieService, TagService tagService, CommentService commentService, CotationService cotationService) {
        this.userService = userService;
        this.addressService = addressService;
        this.topoService = topoService;
        this.sectorService = sectorService;
        this.voieService = voieService;
        this.tagService = tagService;
        this.commentService = commentService;
        this.cotationService = cotationService;
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
        model.addAttribute(PathTable.ATTRIBUTE_COTATION_LIST, cotationService.findAll());

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
        model.addAttribute(PathTable.ATTRIBUTE_COTATION_LIST, cotationService.findAll());

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
        topoDto.setNbLane(0);

        topoDto.setAliasManager(userService.findByEmail(authentication.getName()).getAlias());
        topoDto.setDate(Date.valueOf(LocalDate.now()));
        log.info("/topo/new topo : ", topoDto);
        model.addAttribute(PathTable.ATTRIBUTE_TOPO, topoDto);
        model.addAttribute(PathTable.ATTRIBUTE_ADDRESS, addressDto);
        model.addAttribute(PathTable.ATTRIBUTE_STATUS_LIST, statusList);

        return PathTable.TOPO_ADD;
    }

    @PostMapping("/topo/add")
    public String addTopo(@ModelAttribute("topoDto") @Valid TopoDto topoDto, @NotNull  BindingResult bindingResultTopo,
                          @ModelAttribute("addressDto") @Valid AddressDto addressDto, @NonNull BindingResult bindingResultAddress, Model model) {

        if (bindingResultTopo.hasErrors() || bindingResultAddress.hasErrors()) {
            model.addAttribute(PathTable.ATTRIBUTE_STATUS_LIST, statusList);
            return PathTable.TOPO_ADD;
        }

        // verify that the manager is the Topo manager
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(userService.findByAlias(topoDto.getAliasManager()).getEmail().equals(authentication.getName())) {
            topoDto.setAddressId(addressService.save(addressDto));
            // save and go to update to add images
            return PathTable.TOPO_UPDATE_R + topoService.save(topoDto);
        }

        return PathTable.TOPO_MYLIST_R;
    }

    @GetMapping("/topo/read/{topoId}")
    public String readTopo(@PathVariable("topoId") Integer topoId, Model model) {
        TopoDto topoDto = topoService.getOne(topoId);

        model.addAttribute(PathTable.ATTRIBUTE_TOPO, topoDto);
        model.addAttribute(PathTable.ATTRIBUTE_SECTOR_LIST, sectorService.findByTopoId(topoId));
        model.addAttribute(PathTable.ATTRIBUTE_VOIE_LIST, voieService.findByParentId(topoId));
        model.addAttribute(PathTable.ATTRIBUTE_ADDRESS,addressService.getOne(topoDto.getAddressId()));
        model.addAttribute(PathTable.ATTRIBUTE_COMMENT_LIST, commentService.getBySiteId(topoId));
        model.addAttribute(PathTable.ATTRIBUTE_COTATION_LIST, cotationService.findAll());
        model.addAttribute("tags", tagService.findByTopoId(topoId));

        return PathTable.TOPO_READ;
    }

    @GetMapping("/topo/edit/{topoId}")
    public String editTopo(@PathVariable("topoId") Integer topoId, Model model) {
        TopoDto topoDto = topoService.getOne(topoId);

        model.addAttribute(PathTable.ATTRIBUTE_TOPO, topoDto);
        model.addAttribute(PathTable.ATTRIBUTE_SECTOR_LIST, sectorService.findByTopoId(topoId));
        model.addAttribute(PathTable.ATTRIBUTE_VOIE_LIST, voieService.findByParentId(topoId));
        model.addAttribute(PathTable.ATTRIBUTE_ADDRESS,addressService.getOne(topoDto.getAddressId()));
        model.addAttribute(PathTable.ATTRIBUTE_STATUS_LIST, statusList);
        model.addAttribute(PathTable.ATTRIBUTE_COTATION_LIST, cotationService.findAll());

        return PathTable.TOPO_UPDATE;
    }

    @PostMapping("/topo/update/{topoId}")
    public String updateTopo(@PathVariable("topoId") Integer topoId,
                             @ModelAttribute("topoDto") @Valid TopoDto topoDto, @NotNull  BindingResult bindingResultTopo,
                             @ModelAttribute("addressDto") @Valid AddressDto addressDto, @NonNull BindingResult bindingResultAddress, Model model) {
        if(bindingResultTopo.hasErrors() || bindingResultAddress.hasErrors()){
            model.addAttribute(PathTable.ATTRIBUTE_SECTOR_LIST, sectorService.findByTopoId(topoId));
            model.addAttribute(PathTable.ATTRIBUTE_VOIE_LIST, voieService.findByParentId(topoId));
            model.addAttribute(PathTable.ATTRIBUTE_ADDRESS,addressService.getOne(topoDto.getAddressId()));
            model.addAttribute(PathTable.ATTRIBUTE_STATUS_LIST, statusList);
            model.addAttribute(PathTable.ATTRIBUTE_COTATION_LIST, cotationService.findAll());
            return PathTable.TOPO_UPDATE;
        }

        // verify that the manager is the Topo manager
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(userService.findByAlias(topoDto.getAliasManager()).getEmail().equals(authentication.getName())) {
            topoDto.setAddressId(addressService.save(addressDto));
            topoService.save(topoDto);
        }

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

        return PathTable.TOPO_MYLIST_R;
    }

}
