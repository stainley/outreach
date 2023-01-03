package ca.appolizer.outreach.data.mapper;

public interface ModelMapper<T, Model> {
    T mapFromModel(Model model);

    Model mapToEntity(T t);

}
