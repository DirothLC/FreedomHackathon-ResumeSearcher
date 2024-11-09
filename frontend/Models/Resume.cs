namespace Frontend.Models
{
    public class Resume
    {
        public required string FullText { get; set; }
        public required string Phone { get; set; }
        public required string Email { get; set; }
        public required string Sex { get; set; }
        public required string Experience { get; set; }

        public override string ToString()
        {
            return $"{FullText}\nPhone: {Phone}\nEmail: {Email}\nSex: {Sex}\nExperience: {Experience}";
        }
    }
}