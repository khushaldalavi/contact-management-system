import axios from "axios";

export class ContactSercvices{
    static serverURl=`http://localhost:8080`;
    static getAllContacts(){
        let dataUrl= `${this.serverURl}/contacts`
        return axios.get(dataUrl)
        // return axios.get(`http://localhost:8000/contacts`)
    }

    static getContact(contactId){
        // let dataUrl= `${this.serverURl}/contacts/${contactId}`
        // return axios.get(dataUrl)
        let dataUrl= `http://localhost:8080/contact/${contactId}`
        return axios.get(dataUrl)
    }

    static addConatact(contact){
        // let dataUrl= `${this.serverURl}/contacts`
        // return axios.post(dataUrl,contact)
        return axios.post('http://localhost:8080/contact',contact)
    }

    // static updateContact(contact,contactId){
    //     // let dataUrl= `${this.serverURl}/contacts/${contactId}`
    //     let dataUrl= `http://localhost:8000/contact/${contactId}`
    //     return axios.put(dataUrl,contact)
    // }

    static updateContact(contact){
       
        return axios.patch('http://localhost:8080/contact',contact)
    }

    static deleteContact(contactId){
        // let dataUrl= `${this.serverURl}/contacts/${contactId}`
        let dataUrl= `http://localhost:8080/contact/${contactId}`
        return axios.delete(dataUrl)
    }
}