package com.dsf.escalade.web.controller.global;

import com.dsf.escalade.model.business.StatusType;
import com.dsf.escalade.service.business.SectorService;
import com.dsf.escalade.service.business.TagService;
import com.dsf.escalade.service.business.TopoService;
import com.dsf.escalade.service.business.VoieService;
import com.dsf.escalade.service.global.AddressService;
import com.dsf.escalade.service.global.CommentService;
import com.dsf.escalade.service.global.UserService;
import com.dsf.escalade.web.controller.path.PathTable;
import com.dsf.escalade.web.dto.CommentDto;
import com.dsf.escalade.web.dto.SectorDto;
import com.dsf.escalade.web.dto.TopoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Controller
@RefreshScope
public class CommentController {

    private final UserService userService;
    private final AddressService addressService;
    private final TopoService topoService;
    private final SectorService sectorService;
    private final VoieService voieService;
    private final TagService tagService;
    private final CommentService commentService;
    private final  List<String> statusList =  Stream.of(StatusType.values()).map(Enum::name).collect(Collectors.toList());

    @Autowired
    public CommentController(UserService userService, AddressService addressService, TopoService topoService, SectorService sectorService, VoieService voieService, TagService tagService, CommentService commentService) {
        this.userService = userService;
        this.addressService = addressService;
        this.topoService = topoService;
        this.sectorService = sectorService;
        this.voieService = voieService;
        this.tagService = tagService;
        this.commentService = commentService;
    }

    @PostMapping("/topo/comment/{topoId}")
    public String addTopoComment(@PathVariable("topoId") Integer topoId, @ModelAttribute("commentaire") String commentaire, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        TopoDto topoDto = topoService.getOne(topoId);
        List<SectorDto> sectorDtoList = sectorService.findByTopoId(topoId);

        //don't save anonymous comment
        if(!authentication.getName().equals("anonymousUser") && !commentaire.equals("")) {
            CommentDto commentDto = new CommentDto();
            commentDto.setSiteId(topoId);
            commentDto.setText(commentaire);
            log.info("User : " + authentication.getName() + " add comment");
            commentDto.setAlias(userService.findByEmail(authentication.getName()).getAlias());
            commentService.save(commentDto);
            //incrementation of topo comment number
            if(topoDto.getNbComment()!=null) {
                topoDto.setNbComment(topoDto.getNbComment() + 1);
            } else {
                topoDto.setNbComment(1);
            }

            topoService.save(topoDto);
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
            Integer nbComment = topoDto.getNbComment()-1;
            if (nbComment >= 0){
                topoDto.setNbComment(nbComment);
                topoService.save(topoDto);
            }
        }

        model.addAttribute(PathTable.ATTRIBUTE_TOPO, topoDto);
        model.addAttribute(PathTable.ATTRIBUTE_SECTOR_LIST, sectorDtoList);
        model.addAttribute(PathTable.ATTRIBUTE_VOIE_LIST, voieService.findByParentId(topoId));
        model.addAttribute(PathTable.ATTRIBUTE_ADDRESS,addressService.getOne(topoDto.getAddressId()));
        model.addAttribute(PathTable.ATTRIBUTE_COMMENT_LIST, commentService.getBySiteId(siteId));
        model.addAttribute("tags", tagService.findByTopoId(topoId));

        return PathTable.TOPO_READ_R + siteId;
    }

    @PostMapping("/sector/comment/{sectorId}")
    public String addSectorComment(@PathVariable("sectorId") Integer sectorId, @ModelAttribute("commentaire") String commentaire, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SectorDto sectorDto = sectorService.getOne(sectorId);
        Integer topoId = sectorDto.getTopoId();
        TopoDto topoDto = topoService.getOne(topoId);

        //don't save anonymous comment
        if(!authentication.getName().equals("anonymousUser") && !commentaire.equals("")) {
            CommentDto commentDto = new CommentDto();
            commentDto.setSiteId(sectorId);
            commentDto.setText(commentaire);
            log.info("User : " + authentication.getName() + " add comment");
            commentDto.setAlias(userService.findByEmail(authentication.getName()).getAlias());
            commentService.save(commentDto);
            //incrementation of topo comment number
            //incrementation of topo comment number
            if(topoDto.getNbComment()!=null) {
                topoDto.setNbComment(topoDto.getNbComment() + 1);
            } else {
                topoDto.setNbComment(1);
            }
            topoService.save(topoDto);
        }

        model.addAttribute(PathTable.ATTRIBUTE_TOPO, topoDto);
        model.addAttribute(PathTable.ATTRIBUTE_SECTOR, sectorDto);
        model.addAttribute(PathTable.ATTRIBUTE_SECTOR_LIST, sectorService.findByTopoId(topoId));
        model.addAttribute(PathTable.ATTRIBUTE_COMMENT_LIST, commentService.getBySiteId(sectorId));

        return PathTable.SECTOR_READ_R + sectorId;
    }

    @GetMapping("/sector/comment/delete/{commentId}")
    public String deleteSectorComment(@PathVariable("commentId") Integer commentId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CommentDto commentDto = commentService.getOne(commentId);
        Integer sectorId = commentDto.getSiteId();
        SectorDto sectorDto = sectorService.getOne(sectorId);
        Integer topoId = sectorDto.getTopoId();
        TopoDto topoDto = topoService.getOne(topoId);
        List<SectorDto> sectorDtoList = sectorService.findByTopoId(topoId);

        // verify that the manager is the Topo manager
        if( authentication.getAuthorities().stream()
              .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))){
            commentService.delete(commentDto);
            Integer nbComment = topoDto.getNbComment()-1;
            if (nbComment >= 0){
                topoDto.setNbComment(nbComment);
                topoService.save(topoDto);
            }
        }

        model.addAttribute(PathTable.ATTRIBUTE_TOPO, topoDto);
        model.addAttribute(PathTable.ATTRIBUTE_SECTOR, sectorDto);
        model.addAttribute(PathTable.ATTRIBUTE_SECTOR_LIST, sectorService.findByTopoId(topoId));
        model.addAttribute(PathTable.ATTRIBUTE_COMMENT_LIST, commentService.getBySiteId(sectorId));

        return PathTable.SECTOR_READ_R + sectorId;
    }

}
