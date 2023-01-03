package ca.appolizer.outreach.data.dto;

public interface ModelMapper<T, Model> {

    T mapFromModel(Model model);

    Model mapToEntity(T t);

}
