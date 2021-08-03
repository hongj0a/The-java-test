package me.whiteship.inflearnthejavatest.study;

import me.whiteship.inflearnthejavatest.domain.Member;
import me.whiteship.inflearnthejavatest.domain.Study;
import me.whiteship.inflearnthejavatest.member.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

    @Mock
    MemberService memberService;

    @Mock
    StudyRepository studyRepository;

    @Test
    void createStudyService() {
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("hongjin@email.com");

        Study study = new Study(10, "test");

        when(memberService.findById(1L)).thenReturn(Optional.of(member));
        when(studyRepository.save(study)).thenReturn(study);

        studyService.createNewStudy(1L, study);

        assertEquals(member, study.getOwner());

        verify(memberService, times(1)).notify(study);
        //verifyNoMoreInteractions(memberService);
        verify(memberService, times(1)).notify(member);
        verify(memberService, never()).validate(any());

        InOrder inOrder = inOrder(memberService);
        inOrder.verify(memberService).notify(study);
        inOrder.verify(memberService).notify(member);

    }

}
/*    when(memberService.findById(1L)) //any...?
        .thenReturn(Optional.of(member))
        .thenThrow(new RuntimeException())
        .thenReturn(Optional.empty());

        Optional<Member> byId = memberService.findById(1L);
        assertEquals("hongjin@email.com", byId.get().getEmail());

        assertThrows(RuntimeException.class, () -> {
        memberService.validate(1L);
        assertEquals(Optional.empty(), memberService.findById(1L));
        });*/
/*   when(memberService.findById(1L)).thenReturn(Optional.of(member));
        //when(memberService.findById(1L)).thenReturn(Optional.of(member)); argument parameter, agrument matchers


        Study study = new Study(10, "java");

        assertEquals("hongjin@email.com", memberService.findById(1L).get().getEmail());
       // assertEquals("hongjin@email.com", memberService.findById(2L).get().getEmail());
//        studyService.createNewStudy(1L, study);

        doThrow(new IllegalArgumentException()).when(memberService).validate(1l);

        assertThrows(IllegalArgumentException.class, () -> {
            memberService.validate(1L);
        });

        memberService.validate(1L);
*/
        /*Optional<Member> optional = memberService.findById(1L);
        memberService.validate(2L);*/
        //assertNull(optional); optional.empty optional 타입인경우

/*        MemberService memberService = new MemberService() {
            @Override
            public Optional<Member> findById(long memberId) {
                return Optional.empty();
            }
        };

        StudyRepository studyRepository = new StudyRepository() {
            @Override
            public List<Study> findAll() {
                return null;
            }

            @Override
            public List<Study> findAll(Sort sort) {
                return null;
            }

            @Override
            public List<Study> findAllById(Iterable<Long> iterable) {
                return null;
            }

            @Override
            public <S extends Study> List<S> saveAll(Iterable<S> iterable) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends Study> S saveAndFlush(S s) {
                return null;
            }

            @Override
            public <S extends Study> List<S> saveAllAndFlush(Iterable<S> iterable) {
                return null;
            }

            @Override
            public void deleteAllInBatch(Iterable<Study> iterable) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<Long> iterable) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public Study getOne(Long aLong) {
                return null;
            }

            @Override
            public Study getById(Long aLong) {
                return null;
            }

            @Override
            public <S extends Study> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends Study> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<Study> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Study> S save(S s) {
                return null;
            }

            @Override
            public Optional<Study> findById(Long aLong) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Long aLong) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Long aLong) {

            }

            @Override
            public void delete(Study study) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Long> iterable) {

            }

            @Override
            public void deleteAll(Iterable<? extends Study> iterable) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends Study> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends Study> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Study> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends Study> boolean exists(Example<S> example) {
                return false;
            }
        };*/

        /*MemberService memberService = mock(MemberService.class);
        StudyRepository studyRepository = mock(StudyRepository.class);*/
