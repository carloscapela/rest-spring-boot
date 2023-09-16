package br.com.restfullspringboot.mapper.custom;

import br.com.restfullspringboot.data.vo.v1.BookVO;
import br.com.restfullspringboot.models.Book;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BookMapper {
    //private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();
    public BookVO convertEntityToVo(Book book) {
        BookVO vo = new BookVO();
        vo.setKey(book.getId());
        vo.setAuthor(book.getAuthor());
        vo.setLaunch_date(new Date());
        vo.setPrice(book.getPrice());
        vo.setTitle(book.getTitle());
        return vo;
    }

    public Book convertVoToEntity(BookVO bookVO) {
        Book entity = new Book();
        entity.setId(bookVO.getKey());
        entity.setAuthor(bookVO.getAuthor());
        entity.setLaunch_date(bookVO.getLaunch_date());
        entity.setPrice(bookVO.getPrice());
        entity.setTitle(bookVO.getTitle());

        return entity;
    }
}
