package lab.it.arpan.flopkartspring.converter;

public interface Converter<SourceType, DestinationType> {
    
    DestinationType convert(SourceType source);

}
