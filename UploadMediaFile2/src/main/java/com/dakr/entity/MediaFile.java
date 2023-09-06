package com.dakr.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity 
@Table(name = "Media_File")
public class MediaFile {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	// This annotation is used to specify that a field should be treated as a Large Object (LOB) in a relational database.
	//Large Objects are used to store large amounts of data such as 
	//text, binary data, images, audio, or video files in a database column.
	@Lob 
	// This annotation is used to specify the database column definition for a field as a BLOB (Binary Large Object) type.
	// @Column(columnDefinition = "BLOB")
	
	//The specific use of @Column(columnDefinition = "LONGBLOB") is related to specifying the database column type for a particular field in your entity class
	@Column(columnDefinition = "LONGBLOB") 

	// For storing the file content
	private byte[] data;

}







//@Column: This annotation is used to specify the mapping of a field to a database column. It allows you to customize various attributes of the column.

//columnDefinition: This attribute allows you to provide a custom SQL column definition for the annotated field. It lets you define the data type and any other attributes of the corresponding database column.

//"BLOB": This value is provided as the custom column definition. In the context of databases, "BLOB" stands for Binary Large Object, which is used to store binary data like images, audio, video, documents, etc.

//"LONGBLOB" is a column type in MySQL databases, representing a binary large object (BLOB) capable of storing large amounts of binary data, such as images, videos, audio files, etc.