package com.ftiland.travelrental.member.service;

import com.ftiland.travelrental.common.exception.BusinessLogicException;
import com.ftiland.travelrental.image.service.ImageService;
import com.ftiland.travelrental.member.dto.MemberDto;
import com.ftiland.travelrental.member.dto.MemberPatchDto;
import com.ftiland.travelrental.member.entity.Member;
import com.ftiland.travelrental.member.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static com.ftiland.travelrental.common.exception.ExceptionCode.MEMBER_NOT_FOUND;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final ImageService imageService;

    @Autowired
    public MemberService(MemberRepository memberRepository,ImageService imageService) {
        this.memberRepository = memberRepository;
        this.imageService = imageService;
    }

    public Member createMember(Member member) {
        if(!existsEmail(member.getEmail())) {
            Member savedMember = memberRepository.save(member);
            return savedMember;
        }

        return null;
    }


    public boolean existsEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        return member.isPresent();
    }

    public Member findMember(Long memberId){
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessLogicException(MEMBER_NOT_FOUND));
    }

    public Member findMemberByEmail(String email){
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessLogicException(MEMBER_NOT_FOUND));
    }

    public MemberDto.Response updateMember(String displayName, MultipartFile imageFile, Long memberId) {


        Member member = findMember(memberId);
        String imageUrl = imageService.storeImageMember(imageFile, memberId).getImageUrl();
        member.setDisplayName(displayName);
        member.setImageUrl(imageUrl);
        memberRepository.save(member);
        return MemberDto.Response.from(member, imageUrl);
    }

    public void deleteMember(Long memberId) {
        Member member = findMember(memberId);

        memberRepository.deleteById(memberId);
    }

}
