package com.dsf.escalade.web.controller.business;

import com.dsf.escalade.model.business.StatusType;
import com.dsf.escalade.service.business.SectorService;
import com.dsf.escalade.service.business.TagService;
import com.dsf.escalade.service.business.TopoService;
import com.dsf.escalade.service.business.VoieService;
import com.dsf.escalade.service.global.AddressService;
import com.dsf.escalade.service.global.CommentService;
import com.dsf.escalade.web.controller.path.PathTable;
import com.dsf.escalade.web.dto.TagDto;
import com.dsf.escalade.web.dto.TopoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Controller
@RefreshScope
public class TagController {

    private final AddressService addressService;
    private final TopoService topoService;
    private final SectorService sectorService;
    private final VoieService voieService;
    private final TagService tagService;
    private final CommentService commentService;
    private final  List<String> statusList =  Stream.of(StatusType.values()).map(Enum::name).collect(Collectors.toList());

    @Autowired
    public TagController( AddressService addressService, TopoService topoService, SectorService sectorService, VoieService voieService, TagService tagService, CommentService commentService) {
        this.addressService = addressService;
        this.topoService = topoService;
        this.sectorService = sectorService;
        this.voieService = voieService;
        this.tagService = tagService;
        this.commentService = commentService;
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
