package com.example.nienluan.services.impls;

import com.example.nienluan.configs.PhoneSpecification;
import com.example.nienluan.dto.CreatePicture;
import com.example.nienluan.dto.PhoneRequest;
import com.example.nienluan.dto.PhoneResponse;
import com.example.nienluan.dto.UpdatePhoneDTO;
import com.example.nienluan.exceptions.AppException;
import com.example.nienluan.mappers.PhoneMapper;
import com.example.nienluan.mappers.PictureMapper;
import com.example.nienluan.models.Category;
import com.example.nienluan.models.Manufacturer;
import com.example.nienluan.models.Phone;
import com.example.nienluan.models.Picture;
import com.example.nienluan.repository.CategoryRepository;
import com.example.nienluan.repository.ManufacturerRepository;
import com.example.nienluan.repository.PhoneRepository;
import com.example.nienluan.repository.PictureRepository;
import com.example.nienluan.services.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhoneServiceImpl implements PhoneService {

  @Autowired
  private PhoneRepository phoneRepository;
  @Autowired
  private PhoneMapper phoneMapper;

  @Autowired
  private ManufacturerRepository manufacturerRepository;

  @Autowired
  private CategoryRepository categoryRepository;
  @Autowired
  private PictureRepository pictureRepository;
  @Autowired
  private PictureMapper pictureMapper;

  @Override
  public Page<Phone> getPhones(int page, int size) {
    PageRequest pageRequest = PageRequest.of(page, size);
    Page<Phone> phoneList = phoneRepository.findAll(pageRequest);
//    phoneList.get().map((Phone::getId) -> {})
//    Manufacturer manufacturer = manufacturerRepository.findById();
    return phoneList;
  }

  @Override
  public PhoneResponse addPhone(PhoneRequest phoneRequest) {
    Optional<Phone> phoneOptional = phoneRepository.findByName(phoneRequest.getName());
    if (phoneOptional.isPresent()) {
      throw new AppException("Dien thoai da ton tai", HttpStatus.BAD_GATEWAY);
    }
    Phone phone = phoneMapper.toPhone(phoneRequest);
    Optional<Manufacturer> manufacturer = manufacturerRepository.findById(phoneRequest.getManufacturer());
    if (manufacturer.isEmpty()) {
      throw new AppException("Khong tim thay nha san xuat", HttpStatus.NOT_FOUND);
    }
    phone.setManufacturer(manufacturer.get());
    Optional<Category> category = categoryRepository.findById(phoneRequest.getCategory());
    if (category.isEmpty()) {
      throw new AppException("Khong tim thay phan loai", HttpStatus.NOT_FOUND);
    }

    phone.setCategory(category.get());
    Phone phone1 = phoneRepository.save(phone);
    List<Picture> pictureList = new ArrayList<>();
    for (CreatePicture pic: phoneRequest.getPicture()
    ) {
      if(pictureRepository.findByCaption(pic.getCaption()).isEmpty()){
        pictureRepository.save(pictureMapper.toPicture(pic, phone1));
      }
    }
    return phoneMapper.toPhoneResponse(phone1.getId(), phoneRequest.getName());
  }

  @Override
  public Page<Phone> getListPhoneByManufacturer(Integer id, int page, int size) {
    PageRequest pageRequest = PageRequest.of(page, size);
    Optional<Manufacturer> manufacturer = manufacturerRepository.findById(id);
    Page<Phone> phones = phoneRepository.findAllByManufacturer(manufacturer.get(), pageRequest);
    if(phones.isEmpty()){
      throw new AppException("Hien tai khong co dien thoai nao thuoc hang nay",HttpStatus.NOT_FOUND);
    }



    return phones;
  }

  @Override
  public Phone getPhone(int id) {
    Optional<Phone> phone = phoneRepository.findById(id);
    if(phone.isEmpty()){
      throw new AppException("Phone is not exist", HttpStatus.NOT_FOUND);
    }
    return phone.get();
  }

  public void deletePhone(int id) {
    Optional<Phone> optionalPhone = phoneRepository.findById(id);
    Phone phone = optionalPhone.get();

    List<Picture> pictures = pictureRepository.findAllByPhone(phone);
    if (pictures != null && !pictures.isEmpty()) {
      for (Picture pic : pictures) {
        pictureRepository.delete(pic);
      }
    }
    phone.setManufacturer(null);
    phone.setCategory(null);

    phoneRepository.save(phone);
    phoneRepository.delete(phone);
  }

  public List<Phone> getAllPhones() {
    return phoneRepository.findAll();
  }
  public List<Phone> searchPhones(String name) {
    System.out.println(String.format("%%%s%%", name));
    Specification<Phone> spec = Specification.where(null);
    if (name != null) {
      spec = spec.and(PhoneSpecification.hasName(String.format("%%%s%%", name)));
    }
    List<Phone> phones = phoneRepository.findAll(spec);
    return phones;
  }
  public void updatePhone(int id, UpdatePhoneDTO updatePhoneDTO) {
  Optional<Phone> phoneOptional = phoneRepository.findById(id);
  if(phoneOptional.isEmpty()){
    throw new AppException("Phone is not exist", HttpStatus.NOT_FOUND);
  }
  Phone phone = phoneOptional.get();
    if (!updatePhoneDTO.getDescription().equalsIgnoreCase(phone.getDescription())) {
      phone.setDescription(updatePhoneDTO.getDescription());
    }
    if (!updatePhoneDTO.getChip().equalsIgnoreCase(phone.getChip())) {
      phone.setChip(updatePhoneDTO.getChip());
    }
    if (!updatePhoneDTO.getName().equalsIgnoreCase(phone.getName())) {
      phone.setName(updatePhoneDTO.getName());
    }
    if (updatePhoneDTO.getQuantity() == phone.getQuantity()) {
      phone.setQuantity(updatePhoneDTO.getQuantity());
    }
    if (updatePhoneDTO.getPin() == phone.getPin()) {
      phone.setPin(updatePhoneDTO.getPin());
    }
    if (updatePhoneDTO.getRam() == phone.getRam()) {
      phone.setRam(updatePhoneDTO.getRam());
    }
    if (updatePhoneDTO.getRom() == phone.getRom()) {
      phone.setRom(updatePhoneDTO.getRom());
    }
    if (updatePhoneDTO.getPrice() == phone.getPrice()) {
      phone.setPrice(updatePhoneDTO.getPrice());
    }

    if (updatePhoneDTO.getManufacturer() == phone.getManufacturer().getId()) {
      Optional<Manufacturer> manufacturer = manufacturerRepository.findById(phone.getManufacturer().getId());
      phone.setManufacturer(manufacturer.get());
    }
    if (updatePhoneDTO.getCategory() == phone.getCategory().getId()) {
      Optional<Category> category = categoryRepository.findById(phone.getManufacturer().getId());
      phone.setCategory(category.get());
    }
    List<CreatePicture> createPictures = updatePhoneDTO.getPicture();
    for (int i = 0; i< createPictures.size(); i++) {
      if(!createPictures.get(i).getCaption().equalsIgnoreCase(phone.getPictures().get(i).getCaption())
              || !createPictures.get(i).getImagePath().equalsIgnoreCase(phone.getPictures().get(i).getImagePath())){
        Optional<Picture> picture = pictureRepository.findById(phone.getPictures().get(i).getId());
        if(picture.isEmpty()){
          throw new AppException("Không tìm thấy ảnh", HttpStatus.NOT_FOUND);
        }
        System.out.println(createPictures.get(i).getCaption());
        picture.get().setImagePath(createPictures.get(i).getImagePath());
        picture.get().setCaption(createPictures.get(i).getCaption());
        pictureRepository.save(picture.get());
      }
      phoneRepository.save(phone);
      System.out.println("update success");
    }

  }
}
